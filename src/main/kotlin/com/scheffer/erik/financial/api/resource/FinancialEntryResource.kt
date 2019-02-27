package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.events.CreatedResourceEvent
import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.model.apimodels.FinancialEntryApi
import com.scheffer.erik.financial.api.repositories.FinancialEntryRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/financialEntries")
class FinancialEntryResource(private val financialEntryRepository: FinancialEntryRepository,
                             private val publisher: ApplicationEventPublisher) {
    @GetMapping
    fun list(): MutableList<FinancialEntry> = financialEntryRepository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, response: HttpServletResponse) =
            financialEntryRepository.findById(id).let {
                if (it.isPresent) ResponseEntity.ok(it.get()) else ResponseEntity.notFound().build()
            }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody financialEntryApi: FinancialEntryApi, response: HttpServletResponse) =
            financialEntryRepository.save(financialEntryApi.toFinancialEntry()).let {
                publisher.publishEvent(CreatedResourceEvent(this, response, it.id))
                ResponseEntity.status(HttpStatus.CREATED).body(it)
            }
}
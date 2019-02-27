package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.events.CreatedResourceEvent
import com.scheffer.erik.financial.api.exceptionhandler.FinancialExceptionHandler
import com.scheffer.erik.financial.api.exceptions.PersonInactiveException
import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.model.apimodels.FinancialEntryApi
import com.scheffer.erik.financial.api.repositories.FinancialEntryRepository
import com.scheffer.erik.financial.api.service.FinancialEntryService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("/financialEntries")
class FinancialEntryResource(private val financialEntryRepository: FinancialEntryRepository,
                             private val financialEntryService: FinancialEntryService,
                             private val publisher: ApplicationEventPublisher,
                             private val messageSource: MessageSource) {
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
            financialEntryService.save(financialEntryApi.toFinancialEntry()).let {
                publisher.publishEvent(CreatedResourceEvent(this, response, it.id))
                ResponseEntity.status(HttpStatus.CREATED).body(it)
            }

    @ExceptionHandler(PersonInactiveException::class)
    fun handlePersonInactiveException(ex: PersonInactiveException): ResponseEntity<Any> {
        val userMessage = messageSource.getMessage("person.person-inactive", null, LocaleContextHolder.getLocale())
        val developerMessage = ex.toString()
        return ResponseEntity.badRequest()
                .body(listOf(FinancialExceptionHandler.ErrorMessageWrapper(userMessage, developerMessage)))
    }
}
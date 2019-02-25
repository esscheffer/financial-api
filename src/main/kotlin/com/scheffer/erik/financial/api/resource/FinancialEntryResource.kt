package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repositories.FinancialEntryRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/financialEntries")
class FinancialEntryResource(private val financialEntryRepository: FinancialEntryRepository) {
    @GetMapping
    fun list(): MutableList<FinancialEntry> = financialEntryRepository.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, response: HttpServletResponse) =
            financialEntryRepository.findById(id).let {
                if (it.isPresent) ResponseEntity.ok(it.get()) else ResponseEntity.notFound().build()
            }
}
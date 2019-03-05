package com.scheffer.erik.financial.api.service

import com.scheffer.erik.financial.api.exceptions.PersonInactiveException
import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repository.FinancialEntryRepository
import com.scheffer.erik.financial.api.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class FinancialEntryService(private val financialEntryRepository: FinancialEntryRepository,
                            private val personRepository: PersonRepository) {
    fun save(financialEntry: FinancialEntry): FinancialEntry {
        personRepository.findById(financialEntry.person.id ?: -1)
                .ifPresent { if (!it.active) throw PersonInactiveException() }
        return financialEntryRepository.save(financialEntry)
    }
}
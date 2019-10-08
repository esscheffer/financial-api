package com.scheffer.erik.financial.api.service

import com.scheffer.erik.financial.api.exceptions.PersonInactiveException
import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repository.FinancialEntryRepository
import com.scheffer.erik.financial.api.repository.PersonRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FinancialEntryService(private val financialEntryRepository: FinancialEntryRepository,
                            private val personRepository: PersonRepository) {
    fun save(financialEntry: FinancialEntry): FinancialEntry {
        personRepository.findById(financialEntry.person.id ?: -1)
                .ifPresent { if (!it.active) throw PersonInactiveException() }
        return financialEntryRepository.save(financialEntry)
    }

    fun update(id: Long, financialEntry: FinancialEntry): FinancialEntry {
        val savedFinancialEntry = financialEntryRepository.findByIdOrNull(id) ?: throw IllegalArgumentException()
        val personId = financialEntry.person.id ?: throw IllegalArgumentException()

        if (financialEntry.person.id != savedFinancialEntry.person.id) {
            validatePerson(personId)
        }

        return financialEntryRepository.save(financialEntry.copy(id = savedFinancialEntry.id))
    }

    private fun validatePerson(personId: Long) {
        val person = personRepository.findByIdOrNull(personId)
        if (person == null || !person.active) {
            throw IllegalArgumentException()
        }
    }
}

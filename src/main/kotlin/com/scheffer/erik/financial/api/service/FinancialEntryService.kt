package com.scheffer.erik.financial.api.service

import com.scheffer.erik.financial.api.Mailer
import com.scheffer.erik.financial.api.exceptions.PersonInactiveException
import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repository.FinancialEntryRepository
import com.scheffer.erik.financial.api.repository.PersonRepository
import com.scheffer.erik.financial.api.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate

private const val RECIPIENT_ROLE = "ROLE_SEARCH_FINANCIAL_ENTRY"

@Service
class FinancialEntryService(private val financialEntryRepository: FinancialEntryRepository,
                            private val personRepository: PersonRepository,
                            private val userRepository: UserRepository,
                            private val mailer: Mailer) {

    companion object {
        @JvmStatic
        private val logger = LoggerFactory.getLogger(FinancialEntryService::class.java)
    }

    @Scheduled(cron = "0 0 6 * * *")
    fun warnFinancialEntriesDue() {
        logger.debug("Preparing e-mails warning.")

        val financialEntriesDue =
                financialEntryRepository.findByDueDateLessThanEqualAndPaymentDateIsNull(LocalDate.now())
        if (financialEntriesDue.isEmpty()) {
            logger.info("No financial entry due.")
            return
        }

        val recipients = userRepository.findByPermissionsDescription(RECIPIENT_ROLE)
        if (recipients.isEmpty()) {
            logger.warn("There are financial entries due, but no recipient to send e-mail.")
            return
        }

        mailer.warnFinancialEntriesDue(financialEntriesDue, recipients)
        logger.info("Warn e-mails finished.")
    }

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

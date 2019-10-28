package com.scheffer.erik.financial.api.repository

import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repository.financialentry.FinancialEntryRepositoryQuery
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface FinancialEntryRepository : JpaRepository<FinancialEntry, Long>, FinancialEntryRepositoryQuery {
    fun findByDueDateLessThanEqualAndPaymentDateIsNull(date: LocalDate): List<FinancialEntry>
}

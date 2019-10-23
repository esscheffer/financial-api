package com.scheffer.erik.financial.api.repository.financialentry

import com.scheffer.erik.financial.api.dto.FinancialEntryStatisticCategory
import com.scheffer.erik.financial.api.dto.FinancialEntryStatisticDay
import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repository.filter.FinancialEntryFilter
import com.scheffer.erik.financial.api.repository.projection.FinancialEntrySummary
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface FinancialEntryRepositoryQuery {
    fun filter(financialEntryFilter: FinancialEntryFilter, pageable: Pageable): Page<FinancialEntry>
    fun summary(financialEntryFilter: FinancialEntryFilter, pageable: Pageable): Page<FinancialEntrySummary>
    fun byCategory(referenceMonth: LocalDate): List<FinancialEntryStatisticCategory>
    fun byDay(referenceMonth: LocalDate): List<FinancialEntryStatisticDay>
}

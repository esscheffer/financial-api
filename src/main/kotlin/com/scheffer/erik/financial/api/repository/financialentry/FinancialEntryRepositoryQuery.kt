package com.scheffer.erik.financial.api.repository.financialentry

import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repository.filter.FinancialEntryFilter
import com.scheffer.erik.financial.api.repository.projection.FinancialEntrySummary
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FinancialEntryRepositoryQuery {
    fun filter(financialEntryFilter: FinancialEntryFilter, pageable: Pageable): Page<FinancialEntry>
    fun summary(financialEntryFilter: FinancialEntryFilter, pageable: Pageable): Page<FinancialEntrySummary>
}
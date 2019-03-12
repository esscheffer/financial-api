package com.scheffer.erik.financial.api.repository.financialentry

import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repository.filter.FinancialEntryFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface FinancialEntryRepositoryQuery {
    fun filter(financialEntryFilter: FinancialEntryFilter, pageable: Pageable): Page<FinancialEntry>
}
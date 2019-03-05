package com.scheffer.erik.financial.api.repository.financialentry

import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.repository.filter.FinancialEntryFilter

interface FinancialEntryRepositoryQuery {
    fun filter(financialEntryFilter: FinancialEntryFilter): List<FinancialEntry>
}
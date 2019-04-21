package com.scheffer.erik.financial.api.repository.projection

import com.scheffer.erik.financial.api.model.enumerations.FinancialEntryType
import java.math.BigDecimal
import java.time.LocalDate

data class FinancialEntrySummary(
        var id: Long,
        var entryDescription: String,
        var dueDate: LocalDate,
        var paymentDate: LocalDate?,
        var entryValue: BigDecimal,
        var type: FinancialEntryType,
        var category: String,
        var person: String
)
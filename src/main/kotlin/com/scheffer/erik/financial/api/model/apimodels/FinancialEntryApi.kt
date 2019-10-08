package com.scheffer.erik.financial.api.model.apimodels

import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.model.enumerations.FinancialEntryType
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class FinancialEntryApi(
        val id: Long?,
        @field:NotEmpty val entryDescription: String?,
        @field:NotNull val dueDate: LocalDate?,
        val paymentDate: LocalDate?,
        @field:NotNull val entryValue: BigDecimal?,
        val observation: String?,
        @field:NotNull val type: FinancialEntryType?,
        @field:NotNull val category: CategoryApi?,
        @field:NotNull val person: PersonApi?) {
    fun toFinancialEntry() = FinancialEntry(
            entryDescription = entryDescription!!,
            dueDate = dueDate!!,
            paymentDate = paymentDate,
            entryValue = entryValue!!,
            observation = observation,
            type = type!!,
            category = category!!.toCategory(),
            person = person!!.toPerson())
}

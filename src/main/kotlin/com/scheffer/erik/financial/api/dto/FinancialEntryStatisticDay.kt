package com.scheffer.erik.financial.api.dto

import com.scheffer.erik.financial.api.model.enumerations.FinancialEntryType
import java.math.BigDecimal
import java.time.LocalDate

data class FinancialEntryStatisticDay(val financialEntryType: FinancialEntryType,
                                      val date: LocalDate,
                                      val total: BigDecimal)

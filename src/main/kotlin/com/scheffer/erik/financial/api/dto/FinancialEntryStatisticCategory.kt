package com.scheffer.erik.financial.api.dto

import com.scheffer.erik.financial.api.model.Category
import java.math.BigDecimal

data class FinancialEntryStatisticCategory(val category: Category, val total: BigDecimal)

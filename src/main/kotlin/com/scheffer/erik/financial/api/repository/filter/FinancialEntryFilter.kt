package com.scheffer.erik.financial.api.repository.filter

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class FinancialEntryFilter(val description: String?,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") val dueDateMin: LocalDate?,
                           @DateTimeFormat(pattern = "yyyy-MM-dd") val dueDateMax: LocalDate?)
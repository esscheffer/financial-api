package com.scheffer.erik.financial.api.repositories

import com.scheffer.erik.financial.api.model.FinancialEntry
import org.springframework.data.jpa.repository.JpaRepository

interface FinancialEntryRepository : JpaRepository<FinancialEntry, Long>
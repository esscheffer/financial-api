package com.scheffer.erik.financial.api.model

import com.scheffer.erik.financial.api.model.enumerations.FinancialEntryType
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "financial_entry")
data class FinancialEntry(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var entryDescription: String,
        var dueDate: LocalDate,
        var paymentDate: LocalDate?,
        var entryValue: BigDecimal,
        var observation: String?,
        @Enumerated(EnumType.STRING) var type: FinancialEntryType,
        @ManyToOne @JoinColumn(name = "id_category") var category: Category,
        @ManyToOne @JoinColumn(name = "id_person") var person: Person
)
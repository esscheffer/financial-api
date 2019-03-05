package com.scheffer.erik.financial.api.repository.financialentry

import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.model.FinancialEntry_
import com.scheffer.erik.financial.api.repository.filter.FinancialEntryFilter
import com.scheffer.erik.financial.api.util.ifNotNullOrEmpty
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class FinancialEntryRepositoryImpl(private val entityManager: EntityManager) : FinancialEntryRepositoryQuery {
    override fun filter(financialEntryFilter: FinancialEntryFilter): List<FinancialEntry> {
        val builder = entityManager.criteriaBuilder
        val criteria = builder.createQuery(FinancialEntry::class.java)
        val root = criteria.from(FinancialEntry::class.java)

        val predicates = createRestrictions(financialEntryFilter, builder, root)
        criteria.where(*predicates)

        return entityManager.createQuery<FinancialEntry>(criteria).resultList
    }

    private fun createRestrictions(financialEntryFilter: FinancialEntryFilter, builder: CriteriaBuilder,
                                   root: Root<FinancialEntry>): Array<Predicate> {
        val predicates = ArrayList<Predicate>()

        ifNotNullOrEmpty(financialEntryFilter.description) {
            predicates.add(builder.like(builder.lower(root.get(FinancialEntry_.entryDescription)),
                    "%" + it.toLowerCase() + "%"))
        }

        financialEntryFilter.dueDateMin?.let {
            predicates.add(builder.greaterThanOrEqualTo(root.get(FinancialEntry_.dueDate), it))
        }

        financialEntryFilter.dueDateMax?.let {
            predicates.add(builder.lessThanOrEqualTo(root.get(FinancialEntry_.dueDate), it))
        }

        return predicates.toTypedArray()
    }
}
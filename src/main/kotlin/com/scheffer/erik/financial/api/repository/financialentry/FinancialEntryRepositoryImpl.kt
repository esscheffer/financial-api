package com.scheffer.erik.financial.api.repository.financialentry

import com.scheffer.erik.financial.api.model.FinancialEntry
import com.scheffer.erik.financial.api.model.FinancialEntry_
import com.scheffer.erik.financial.api.repository.filter.FinancialEntryFilter
import com.scheffer.erik.financial.api.util.ifNotNullOrEmpty
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class FinancialEntryRepositoryImpl(private val entityManager: EntityManager) : FinancialEntryRepositoryQuery {
    override fun filter(financialEntryFilter: FinancialEntryFilter, pageable: Pageable): Page<FinancialEntry> {
        val builder = entityManager.criteriaBuilder
        val criteria = builder.createQuery(FinancialEntry::class.java)
        val root = criteria.from(FinancialEntry::class.java)

        criteria.where(*createCriteriaRestrictions(financialEntryFilter, builder, root))

        val query = entityManager.createQuery<FinancialEntry>(criteria)
                .setFirstResult(pageable.pageNumber * pageable.pageSize)
                .setMaxResults(pageable.pageSize)

        return PageImpl(query.resultList,
                pageable, countTotal(financialEntryFilter))
    }

    private fun createCriteriaRestrictions(financialEntryFilter: FinancialEntryFilter, builder: CriteriaBuilder,
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

    private fun countTotal(financialEntryFilter: FinancialEntryFilter): Long {
        val builder = entityManager.criteriaBuilder
        val criteria = builder.createQuery(Long::class.java)
        val root = criteria.from(FinancialEntry::class.java)

        criteria.select(builder.count(root))
                .where(*createCriteriaRestrictions(financialEntryFilter, builder, root))
        return entityManager.createQuery(criteria).singleResult
    }
}
package com.scheffer.erik.financial.api.repository.person

import com.scheffer.erik.financial.api.model.Person
import com.scheffer.erik.financial.api.model.Person_
import com.scheffer.erik.financial.api.repository.filter.PersonFilter
import com.scheffer.erik.financial.api.util.ifNotNullOrEmpty
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class PersonRepositoryImpl(private val entityManager: EntityManager) : PersonRepositoryQuery {
    override fun filter(personFilter: PersonFilter, pageable: Pageable): Page<Person> {
        val builder = entityManager.criteriaBuilder
        val criteria = builder.createQuery(Person::class.java)
        val root = criteria.from(Person::class.java)

        criteria.where(*createCriteriaRestrictions(personFilter, builder, root))

        return PageImpl(getPaginatedQuery(criteria, pageable).resultList,
                pageable, countTotal(personFilter))
    }

    private fun createCriteriaRestrictions(personFilter: PersonFilter, builder: CriteriaBuilder,
                                           root: Root<Person>): Array<Predicate> {
        val predicates = ArrayList<Predicate>()

        ifNotNullOrEmpty(personFilter.name) {
            predicates.add(builder.like(builder.lower(root.get(Person_.name)),
                    "%" + it.toLowerCase() + "%"))
        }

        return predicates.toTypedArray()
    }

    private fun countTotal(personFilter: PersonFilter): Long {
        val builder = entityManager.criteriaBuilder
        val criteria = builder.createQuery(Long::class.java)
        val root = criteria.from(Person::class.java)

        criteria.select(builder.count(root))
                .where(*createCriteriaRestrictions(personFilter, builder, root))
        return entityManager.createQuery(criteria).singleResult
    }

    private fun <T> getPaginatedQuery(criteriaQuery: CriteriaQuery<T>, pageable: Pageable) =
            entityManager.createQuery<T>(criteriaQuery)
                    .setFirstResult(pageable.pageNumber * pageable.pageSize)
                    .setMaxResults(pageable.pageSize)
}

package com.scheffer.erik.financial.api.repository.person

import com.scheffer.erik.financial.api.model.Person
import com.scheffer.erik.financial.api.repository.filter.PersonFilter
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PersonRepositoryQuery {
    fun filter(personFilter: PersonFilter, pageable: Pageable): Page<Person>
}

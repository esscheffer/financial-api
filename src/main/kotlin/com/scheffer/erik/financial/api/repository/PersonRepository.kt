package com.scheffer.erik.financial.api.repository

import com.scheffer.erik.financial.api.model.Person
import com.scheffer.erik.financial.api.repository.person.PersonRepositoryQuery
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long>, PersonRepositoryQuery

package com.scheffer.erik.financial.api.repositories

import com.scheffer.erik.financial.api.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long>
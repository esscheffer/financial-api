package com.scheffer.erik.financial.api.service

import com.scheffer.erik.financial.api.model.Person
import com.scheffer.erik.financial.api.repository.PersonRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository) {
    fun update(id: Long, person: Person) = personRepository.save(person.copy(id = findPersonById(id).id))

    fun updateActive(id: Long, active: Boolean) =
            personRepository.save(findPersonById(id).apply { this.active = active })

    private fun findPersonById(id: Long) =
            personRepository.findById(id).let { if (!it.isPresent) throw EmptyResultDataAccessException(1) else it.get() }
}
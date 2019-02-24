package com.scheffer.erik.financial.api.service

import com.scheffer.erik.financial.api.models.Person
import com.scheffer.erik.financial.api.repositories.PersonRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository) {
    fun update(id: Long, person: Person): Person {
        val saved = findPersonById(id)
        return personRepository.save(person.copy(id = saved.id))
    }

    fun updateActive(id: Long, active: Boolean) =
            personRepository.save(findPersonById(id).apply { this.active = active })

    private fun findPersonById(id: Long): Person {
        val saved = personRepository.findById(id)
        if (!saved.isPresent) {
            throw EmptyResultDataAccessException(1)
        }
        return saved.get()
    }
}
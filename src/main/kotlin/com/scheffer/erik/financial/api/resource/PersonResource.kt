package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.events.CreatedResourceEvent
import com.scheffer.erik.financial.api.model.Person
import com.scheffer.erik.financial.api.model.apimodels.PersonApi
import com.scheffer.erik.financial.api.repositories.PersonRepository
import com.scheffer.erik.financial.api.service.PersonService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("/people")
class PersonResource(private val personRepository: PersonRepository,
                     private val publisher: ApplicationEventPublisher,
                     private val personService: PersonService) {

    @GetMapping
    fun list(): List<Person> = personRepository.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody personApi: PersonApi, response: HttpServletResponse) =
            personRepository.save(personApi.toPerson()).let {
                publisher.publishEvent(CreatedResourceEvent(this, response, it.id))
                ResponseEntity.status(HttpStatus.CREATED).body(it)
            }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, response: HttpServletResponse) =
            personRepository.findById(id).let {
                if (it.isPresent) ResponseEntity.ok(it.get()) else ResponseEntity.notFound().build()
            }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remover(@PathVariable id: Long) = personRepository.deleteById(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody personApi: PersonApi) =
            ResponseEntity.ok(personService.update(id, personApi.toPerson()))

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateActive(@PathVariable id: Long, @RequestBody active: Boolean) = personService.updateActive(id, active)
}
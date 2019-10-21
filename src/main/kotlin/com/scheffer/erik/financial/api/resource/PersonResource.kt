package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.events.CreatedResourceEvent
import com.scheffer.erik.financial.api.model.apimodels.PersonApi
import com.scheffer.erik.financial.api.repository.PersonRepository
import com.scheffer.erik.financial.api.repository.filter.PersonFilter
import com.scheffer.erik.financial.api.service.PersonService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("/people")
class PersonResource(private val personRepository: PersonRepository,
                     private val publisher: ApplicationEventPublisher,
                     private val personService: PersonService) {

    @GetMapping
    fun search(personFilter: PersonFilter, pageable: Pageable) =
            personRepository.filter(personFilter, pageable)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
    fun create(@Valid @RequestBody personApi: PersonApi, response: HttpServletResponse) =
            personRepository.save(personApi.toPerson()).let {
                publisher.publishEvent(CreatedResourceEvent(this, response, it.id))
                ResponseEntity.status(HttpStatus.CREATED).body(it)
            }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_PERSON') and #oauth2.hasScope('read')")
    fun getById(@PathVariable id: Long, response: HttpServletResponse) =
            personRepository.findById(id).let {
                if (it.isPresent) ResponseEntity.ok(it.get()) else ResponseEntity.notFound().build()
            }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVE_PERSON') and #oauth2.hasScope('write')")
    fun remover(@PathVariable id: Long) = personRepository.deleteById(id)

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
    fun update(@PathVariable id: Long, @RequestBody personApi: PersonApi) =
            ResponseEntity.ok(personService.update(id, personApi.toPerson()))

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and #oauth2.hasScope('write')")
    fun updateActive(@PathVariable id: Long, @RequestBody active: Boolean) = personService.updateActive(id, active)
}

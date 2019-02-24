package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.events.CreatedResourceEvent
import com.scheffer.erik.financial.api.exceptionhandler.DuplicateException
import com.scheffer.erik.financial.api.models.Category
import com.scheffer.erik.financial.api.models.apimodels.CategoryApi
import com.scheffer.erik.financial.api.repositories.CategoryRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/categories")
class CategoryResource(private val categoryRepository: CategoryRepository,
                       private val publisher: ApplicationEventPublisher) {

    @GetMapping
    fun list(): List<Category> = categoryRepository.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody categoryApi: CategoryApi, response: HttpServletResponse): ResponseEntity<Category> {
        if (categoryRepository.findByName(categoryApi.name!!) != null) {
            throw DuplicateException("Category", "Name")
        }
        val saved = categoryRepository.save(categoryApi.toCategory())
        publisher.publishEvent(CreatedResourceEvent(this, response, saved.id))
        return ResponseEntity.status(HttpStatus.CREATED).body(saved)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long, response: HttpServletResponse) =
            categoryRepository.findById(id).let {
                if (it.isPresent) ResponseEntity.ok(it.get()) else ResponseEntity.notFound().build()
            }
}
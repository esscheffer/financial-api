package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.events.CreatedResourceEvent
import com.scheffer.erik.financial.api.model.Category
import com.scheffer.erik.financial.api.model.apimodels.CategoryApi
import com.scheffer.erik.financial.api.repository.CategoryRepository
import com.scheffer.erik.financial.api.service.CategoryService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/categories")
class CategoryResource(private val categoryRepository: CategoryRepository,
                       private val categoryService: CategoryService,
                       private val publisher: ApplicationEventPublisher) {

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('read')")
    fun list(): List<Category> = categoryRepository.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_REGISTER_CATEGORY') and #oauth2.hasScope('read')")
    fun create(@Valid @RequestBody categoryApi: CategoryApi, response: HttpServletResponse) =
            categoryService.save(categoryApi.toCategory()).let {
                publisher.publishEvent(CreatedResourceEvent(this, response, it.id))
                ResponseEntity.status(HttpStatus.CREATED).body(it)
            }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_CATEGORY') and #oauth2.hasScope('write')")
    fun getById(@PathVariable id: Long, response: HttpServletResponse) =
            categoryRepository.findById(id).let {
                if (it.isPresent) ResponseEntity.ok(it.get()) else ResponseEntity.notFound().build()
            }
}

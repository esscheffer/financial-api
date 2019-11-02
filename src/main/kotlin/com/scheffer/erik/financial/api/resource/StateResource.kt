package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.model.State
import com.scheffer.erik.financial.api.repository.StateRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/states")
class StateResource(private val stateRepository: StateRepository) {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    fun list(): List<State> = stateRepository.findAll()
}

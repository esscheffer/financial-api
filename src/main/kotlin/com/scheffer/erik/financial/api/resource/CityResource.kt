package com.scheffer.erik.financial.api.resource

import com.scheffer.erik.financial.api.model.City
import com.scheffer.erik.financial.api.repository.CityRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cities")
class CityResource(private val cityRepository: CityRepository) {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    fun search(@RequestParam state: Long): List<City> = cityRepository.findByStateId(state)
}

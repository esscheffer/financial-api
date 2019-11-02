package com.scheffer.erik.financial.api.repository

import com.scheffer.erik.financial.api.model.City
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository : JpaRepository<City, Long> {
    fun findByStateId(id: Long): List<City>
}

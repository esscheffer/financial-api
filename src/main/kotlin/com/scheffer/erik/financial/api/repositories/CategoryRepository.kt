package com.scheffer.erik.financial.api.repositories

import com.scheffer.erik.financial.api.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByName(name: String): Category?
}

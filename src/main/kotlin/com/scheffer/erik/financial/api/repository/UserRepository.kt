package com.scheffer.erik.financial.api.repository

import com.scheffer.erik.financial.api.model.ApiUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<ApiUser, Long> {
    fun findByEmail(email: String): ApiUser?
}
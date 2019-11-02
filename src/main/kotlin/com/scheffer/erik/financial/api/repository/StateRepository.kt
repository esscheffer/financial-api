package com.scheffer.erik.financial.api.repository

import com.scheffer.erik.financial.api.model.State
import org.springframework.data.jpa.repository.JpaRepository

interface StateRepository : JpaRepository<State, Long>

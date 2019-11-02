package com.scheffer.erik.financial.api.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "state")
data class State(@Id var id: Long? = null, var stateName: String = "")

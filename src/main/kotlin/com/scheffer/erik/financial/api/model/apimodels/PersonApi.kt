package com.scheffer.erik.financial.api.model.apimodels

import com.scheffer.erik.financial.api.model.Address
import com.scheffer.erik.financial.api.model.Person
import javax.persistence.Embedded
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class PersonApi(val id: Long?,
                     @field:NotNull @field:Size(min = 1, max = 50) val name: String? = null,
                     @Embedded val address: Address? = null,
                     @field:NotNull val active: Boolean? = null) {
    fun toPerson() = Person(id = id, name = name ?: "", address = address, active = active ?: false)
}
package com.scheffer.erik.financial.api.models.apimodels

import com.scheffer.erik.financial.api.models.Address
import com.scheffer.erik.financial.api.models.Person
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class PersonApi(@field:NotNull @field:Size(min = 1, max = 50) var name: String? = null,
                     @Embedded var address: Address? = null,
                     @field:NotNull var active: Boolean? = null) {
    fun toPerson() = Person(name = name!!, address = address, active = active!!)
}
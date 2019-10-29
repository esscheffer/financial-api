package com.scheffer.erik.financial.api.model.apimodels

import com.scheffer.erik.financial.api.model.Contact
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class ContactApi(
        val id: Long?,
        @field:NotEmpty val name: String?,
        @field:Email @field:NotNull val email: String?,
        @field:NotNull val phoneNumber: String?,
        val person: PersonApi?) {
    fun toContact() = Contact(
            id = id,
            name = name!!,
            email = email!!,
            phoneNumber = phoneNumber!!,
            person = person?.toPerson())
}

package com.scheffer.erik.financial.api.model

import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.Size

@Embeddable
data class Address(@field:Size(min = 1, max = 50) var streetAddress: String? = null,
                   @field:Size(min = 1, max = 30) var number: String? = null,
                   @field:Size(min = 1, max = 30) var complement: String? = null,
                   @field:Size(min = 1, max = 30) var neighborhood: String? = null,
                   @field:Size(min = 1, max = 30) var zipCode: String? = null,
                   @ManyToOne @JoinColumn(name = "id_city") var city: City)

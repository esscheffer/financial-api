package com.scheffer.erik.financial.api.models.apimodels

import com.scheffer.erik.financial.api.models.Category
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CategoryApi(@field:NotNull @field:Size(min = 3, max = 20) var name: String?) {
    fun toCategory() = Category(name = this.name!!)
}
package com.scheffer.erik.financial.api.model.apimodels

import com.scheffer.erik.financial.api.model.Category
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CategoryApi(val id: Long?, @field:NotNull @field:Size(min = 3, max = 20) val name: String?) {
    fun toCategory() = Category(id = id, name = this.name ?: "")
}
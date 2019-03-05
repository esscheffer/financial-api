package com.scheffer.erik.financial.api.service

import com.scheffer.erik.financial.api.exceptions.DuplicateException
import com.scheffer.erik.financial.api.model.Category
import com.scheffer.erik.financial.api.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {
    fun save(category: Category) =
            if (categoryRepository.findByName(category.name) != null) {
                throw DuplicateException("Category", "Name")
            } else {
                categoryRepository.save(category)
            }
}
package com.hafhashtad.app.framework.network.mapper

import CategoryResponse
import com.hafhashtad.app.mapper.DataMapper
import com.hafhashtad.core.domain.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryResponseMapper @Inject constructor() : DataMapper<Category, CategoryResponse>() {

    override fun transformToEntity(model: Category): CategoryResponse? {
        // Unnecessary transform
        return null
    }

    override fun transformToModel(entity: CategoryResponse): Category? {
        return Category(
            entity.id,
            entity.title,
            entity.color
        )
    }
}

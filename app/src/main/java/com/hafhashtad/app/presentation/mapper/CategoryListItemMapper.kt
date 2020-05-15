package com.hafhashtad.app.presentation.mapper

import com.hafhashtad.app.mapper.DataMapper
import com.hafhashtad.app.presentation.model.ListItem
import com.hafhashtad.core.domain.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryListItemMapper @Inject constructor() :
    DataMapper<Category, ListItem.CategoryListItem>() {

    override fun transformToEntity(model: Category): ListItem.CategoryListItem? {
        return ListItem.CategoryListItem(model)
    }

    override fun transformToModel(entity: ListItem.CategoryListItem): Category? {
        // Unnecessary transform
        return null
    }
}

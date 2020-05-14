package com.hafhashtad.app.presentation.mapper

import com.hafhashtad.app.mapper.DataMapper
import com.hafhashtad.app.presentation.model.ListItem
import com.hafhashtad.core.domain.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductListItemMapper @Inject constructor() :
    DataMapper<Product, ListItem.ProductListItem>() {

    override fun transformToEntity(model: Product): ListItem.ProductListItem? {
        return ListItem.ProductListItem(model)
    }

    override fun transformToModel(entity: ListItem.ProductListItem): Product? {
        // Unnecessary transform
        return null
    }
}

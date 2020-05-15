package com.hafhashtad.app.framework.network.mapper

import com.hafhashtad.app.framework.network.response.ProductResponse
import com.hafhashtad.app.mapper.DataMapper
import com.hafhashtad.core.domain.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductResponseMapper @Inject constructor() : DataMapper<Product, ProductResponse>() {

    override fun transformToEntity(model: Product): ProductResponse? {
        // Unnecessary transform
        return null
    }

    override fun transformToModel(entity: ProductResponse): Product? {
        return Product(
            entity.id,
            entity.title,
            entity.categoryColor
        )
    }
}

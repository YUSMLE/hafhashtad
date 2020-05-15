package com.hafhashtad.core.data

import com.hafhashtad.core.domain.Category
import com.hafhashtad.core.domain.Product
import com.hafhashtad.core.domain.Resource

interface StoreDataSource {

    suspend fun getProductList(): Resource<List<Product>>

    suspend fun getCategoryList(): Resource<List<Category>>

    suspend fun changeCategoryOfProduct(productId: Int, newCategoryId: Int): Resource<Product>
}
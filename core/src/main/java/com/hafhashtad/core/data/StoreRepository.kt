package com.hafhashtad.core.data

import com.hafhashtad.core.domain.Category
import com.hafhashtad.core.domain.Product
import com.hafhashtad.core.domain.Resource
import javax.inject.Inject

class StoreRepository @Inject constructor(private val dataSource: StoreDataSource) {

    suspend fun getProductList(): Resource<List<Product>> =
        dataSource.getProductList()

    suspend fun getCategoryList(): Resource<List<Category>> =
        dataSource.getCategoryList()

    suspend fun changeCategoryOfProduct(productId: Int, newCategoryId: Int) =
        dataSource.changeCategoryOfProduct(productId, newCategoryId)
}

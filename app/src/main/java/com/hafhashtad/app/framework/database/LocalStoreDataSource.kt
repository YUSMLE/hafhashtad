package com.hafhashtad.app.framework.database

import android.content.Context
import com.hafhashtad.core.data.StoreDataSource
import com.hafhashtad.core.domain.Category
import com.hafhashtad.core.domain.Product
import com.hafhashtad.core.domain.Resource
import javax.inject.Inject

class LocalStoreDataSource @Inject constructor(context: Context) : StoreDataSource {

    override suspend fun getProductList(): Resource<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryList(): Resource<List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun changeCategoryOfProduct(
        productId: Int,
        newCategoryId: Int
    ): Resource<Product> {
        TODO("Not yet implemented")
    }
}

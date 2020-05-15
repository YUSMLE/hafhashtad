package com.hafhashtad.app.framework.network

import android.util.Log
import com.hafhashtad.app.framework.network.mapper.CategoryResponseMapper
import com.hafhashtad.app.framework.network.mapper.ProductResponseMapper
import com.hafhashtad.core.data.StoreDataSource
import com.hafhashtad.core.domain.Category
import com.hafhashtad.core.domain.Product
import com.hafhashtad.core.domain.Resource
import java.io.IOException
import javax.inject.Inject

class RemoteStoreDataSource @Inject constructor(
    private val service: RemoteStoreService,
    private val productResponseMapper: ProductResponseMapper,
    private val categoryResponseMapper: CategoryResponseMapper
) : StoreDataSource {

    override suspend fun getProductList(): Resource<List<Product>> {
        return try {
            val response = service.fetchProductList()
            return if (response.isSuccessful) {
                Resource.success(productResponseMapper.transformToModels(response.body()!!))
            }
            else {
                /* Handle standard error codes, by checking [response.code()] */

                Resource.error(
                    IOException(response.errorBody()?.string() ?: "Something goes wrong")
                )
            }
        }
        catch (e: Exception) {
            // DEBUG
            Log.e("getProductList", e.message ?: "Internet error runs")

            Resource.error(IOException(e.message ?: "Internet error runs"))
        }
    }

    override suspend fun getCategoryList(): Resource<List<Category>> {
        return try {
            val response = service.fetchCategoryList()
            return if (response.isSuccessful) {
                Resource.success(categoryResponseMapper.transformToModels(response.body()!!))
            }
            else {
                /* Handle standard error codes, by checking [response.code()] */

                Resource.error(
                    IOException(response.errorBody()?.string() ?: "Something goes wrong")
                )
            }
        }
        catch (e: Exception) {
            // DEBUG
            Log.e("getCategoryList", e.message ?: "Internet error runs")

            Resource.error(IOException(e.message ?: "Internet error runs"))
        }
    }

    override suspend fun changeCategoryOfProduct(
        productId: Int,
        newCategoryId: Int
    ): Resource<Product> {
        return try {
            val response = service.updateCategoryOfProduct(productId, newCategoryId)
            return if (response.isSuccessful) {
                Resource.success(productResponseMapper.transformToModel(response.body()!!)!!)
            }
            else {
                /* Handle standard error codes, by checking [response.code()] */

                Resource.error(
                    IOException(response.errorBody()?.string() ?: "Something goes wrong")
                )
            }
        }
        catch (e: Exception) {
            // DEBUG
            Log.e("changeCategoryOfProduct", e.message ?: "Internet error runs")

            Resource.error(IOException(e.message ?: "Internet error runs"))
        }
    }
}

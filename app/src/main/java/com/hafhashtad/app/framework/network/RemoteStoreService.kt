package com.hafhashtad.app.framework.network

import CategoryResponse
import com.hafhashtad.app.framework.network.response.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteStoreService {

    @GET("products")
    suspend fun fetchProductList(): Response<List<ProductResponse>>

    @GET("categories")
    suspend fun fetchCategoryList(): Response<List<CategoryResponse>>

    @POST("products/{product_id}")
    suspend fun updateCategoryOfProduct(
        @Path("product_id") productId: Int,
        @Query("new_category_id") newCategoryId: Int
    ): Response<ProductResponse>
}

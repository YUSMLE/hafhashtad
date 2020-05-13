package com.hafhashtad.core.interactors

import com.hafhashtad.core.data.StoreRepository
import com.hafhashtad.core.domain.Product
import com.hafhashtad.core.domain.Resource
import javax.inject.Inject

class ChangeCategoryOfProduct @Inject constructor(private val storeRepository: StoreRepository) {

    suspend operator fun invoke(productId: Int, newCategoryId: Int): Resource<Product> =
        storeRepository.changeCategoryOfProduct(productId, newCategoryId)
}

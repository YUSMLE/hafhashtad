package com.hafhashtad.core.interactors

import com.hafhashtad.core.data.StoreRepository
import com.hafhashtad.core.domain.Category
import com.hafhashtad.core.domain.Resource
import javax.inject.Inject

class GetCategoryList @Inject constructor(private val storeRepository: StoreRepository) {

    suspend operator fun invoke(): Resource<List<Category>> =
        storeRepository.getCategoryList()
}

package com.hafhashtad.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hafhashtad.app.common.help.RxAwareViewModel
import com.hafhashtad.app.presentation.mapper.CategoryListItemMapper
import com.hafhashtad.app.presentation.mapper.ProductListItemMapper
import com.hafhashtad.app.presentation.viewstate.CategoryListVS
import com.hafhashtad.app.presentation.viewstate.ProductListVS
import com.hafhashtad.core.domain.Category
import com.hafhashtad.core.domain.Product
import com.hafhashtad.core.domain.Resource
import com.hafhashtad.core.domain.Status
import com.hafhashtad.core.interactors.ChangeCategoryOfProduct
import com.hafhashtad.core.interactors.GetCategoryList
import com.hafhashtad.core.interactors.GetProductList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainVM @Inject constructor(
    private val getProductList: GetProductList,
    private val getCategoryList: GetCategoryList,
    private val changeCategoryOfProduct: ChangeCategoryOfProduct,
    private val productListItemMapper: ProductListItemMapper,
    private val categoryListItemMapper: CategoryListItemMapper
) : RxAwareViewModel() {

    private val productsStream = MutableLiveData<ProductListVS>()
    private val categoriesStream = MutableLiveData<CategoryListVS>()

    fun getProductsStream(): LiveData<ProductListVS> = productsStream
    fun getCategoriesStream(): LiveData<CategoryListVS> = categoriesStream

    fun fetchProducts() {
        viewModelScope.launch {
            val resource = getProductList.invoke()
            withContext(Dispatchers.Main) {
                productsStream.postValue(transformResourceToProductViewState(resource))
            }
        }

        // Dispatch loading view state
        productsStream.postValue(buildProductsLoadingViewState())
    }

    fun fetchCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val resource = getCategoryList.invoke()
            withContext(Dispatchers.Main) {
                categoriesStream.postValue(transformResourceToCategoryViewState(resource))
            }
        }

        // Dispatch loading view state
        categoriesStream.postValue(buildCategoriesLoadingViewState())
    }

    private fun transformResourceToProductViewState(
        resource: Resource<List<Product>>,
        page: Int = 1
    ): ProductListVS {
        return ProductListVS(
            resource.status,
            resource.error?.message?.let { buildHumanReadableErrorMessage(it, page) },
            resource.data?.let { productListItemMapper.transformToEntities(it) },
            page
        )
    }

    private fun transformResourceToCategoryViewState(
        resource: Resource<List<Category>>,
        page: Int = 1
    ): CategoryListVS {
        return CategoryListVS(
            resource.status,
            resource.error?.message?.let { buildHumanReadableErrorMessage(it, page) },
            resource.data?.let { categoryListItemMapper.transformToEntities(it) },
            page
        )
    }

    private fun buildProductsLoadingViewState(page: Int = 1): ProductListVS {
        return ProductListVS(status = Status.LOADING, page = page)
    }

    private fun buildCategoriesLoadingViewState(page: Int = 1): CategoryListVS {
        return CategoryListVS(status = Status.LOADING, page = page)
    }

    /**
     * Add some magics here to make error messages more user-friendly... 😊
     */
    private fun buildHumanReadableErrorMessage(message: String, page: Int): String {
        return when (page) {
            1 -> "Unable to load any item! Please check your internet and then swipe to refresh."
            else -> "Unable to load more items! Tap to retry."
        }
    }
}

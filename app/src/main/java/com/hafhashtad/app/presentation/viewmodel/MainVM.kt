package com.hafhashtad.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hafhashtad.app.Const
import com.hafhashtad.app.common.help.RxAwareViewModel
import com.hafhashtad.app.common.help.SingleLiveData
import com.hafhashtad.app.presentation.mapper.CategoryListItemMapper
import com.hafhashtad.app.presentation.mapper.ProductListItemMapper
import com.hafhashtad.app.presentation.model.ListItem
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
    private val selectedProductListItem = SingleLiveData<ListItem.ProductListItem>()

    fun getProductsStream(): LiveData<ProductListVS> = productsStream
    fun getCategoriesStream(): LiveData<CategoryListVS> = categoriesStream
    fun getSelectedProductListItem(): LiveData<ListItem.ProductListItem> = selectedProductListItem

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

    fun setSelectedProductListItem(productListItem: ListItem.ProductListItem) {
        selectedProductListItem.postValue(productListItem)
    }

    fun updateCategoryOfProduct(
        productListItem: ListItem.ProductListItem,
        categoryListItem: ListItem.CategoryListItem
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val resource = changeCategoryOfProduct.invoke(
                productListItem.product.id,
                categoryListItem.category.id
            )

            /**
             * It's a simulated use case. So, update the dataset anyway.
             */

            withContext(Dispatchers.Main) {
                applyNewCategoryColorOfProduct(productListItem, categoryListItem.category.color)
            }
        }

        // Dispatch loading view state of [Product]
        applyNewCategoryColorOfProduct(productListItem, Const.LOADING_COLOR_STRING)
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

    private fun applyNewCategoryColorOfProduct(
        productListItem: ListItem.ProductListItem,
        categoryColor: String
    ) {
        val productListItems = productsStream.value?.getProductListItems()
        productListItems?.find { it.product.id == productListItem.product.id }
            ?.product?.categoryColor = categoryColor

        productsStream.postValue(
            ProductListVS(status = Status.SUCCESS, productListItems = productListItems)
        )
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

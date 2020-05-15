package com.hafhashtad.app.presentation.model

import com.hafhashtad.core.domain.Category
import com.hafhashtad.core.domain.Product

sealed class ListItem(val viewType: ListItemType) {

    data class ProductListItem(val product: Product) : ListItem(ListItemType.PRODUCT_ITEM)

    data class CategoryListItem(val category: Category) : ListItem(ListItemType.CATEGORY_ITEM)

    data class LoadingListItem(val page: Int) : ListItem(ListItemType.LOADING_ITEM)

    data class ErrorListItem(
        val errorMsg: String,
        val page: Int
    ) : ListItem(ListItemType.ERROR_ITEM)
}

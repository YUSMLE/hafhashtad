package com.hafhashtad.app.presentation.viewstate

import com.hafhashtad.app.presentation.model.ListItem
import com.hafhashtad.core.domain.Status

class CategoryListVS(
    private val status: Status,
    private val errorMessage: String? = null,
    private val categoryListItems: List<ListItem.CategoryListItem>? = null,
    val page: Int = 1
) {

    fun isLoadingInitialPage() = status == Status.LOADING && page == 1

    fun isLoadingSequencePages() = status == Status.LOADING && page > 1

    fun shouldShowErrorAnnounce() = errorMessage != null && page == 1

    fun shouldShowErrorToast() = errorMessage != null && page > 1

    fun getErrorMessage() = errorMessage

    fun getCategoryListItems() = categoryListItems ?: mutableListOf()

    fun getLoadingListItem() = if (isLoadingSequencePages()) {
        ListItem.LoadingListItem(page)
    }
    else {
        null
    }

    fun getErrorListItem() = if (shouldShowErrorToast()) {
        ListItem.ErrorListItem(errorMessage!!, page)
    }
    else {
        null
    }
}

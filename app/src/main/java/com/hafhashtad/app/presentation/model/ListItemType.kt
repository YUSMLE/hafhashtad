package com.hafhashtad.app.presentation.model

enum class ListItemType(val value: Int) {

    PRODUCT_ITEM(0), CATEGORY_ITEM(1), LOADING_ITEM(2), ERROR_ITEM(3);

    companion object {
        fun ofValue(value: Int): ListItemType? {
            for (userIssue in values()) {
                if (userIssue.value == value) {
                    return userIssue
                }
            }

            return null
        }
    }
}

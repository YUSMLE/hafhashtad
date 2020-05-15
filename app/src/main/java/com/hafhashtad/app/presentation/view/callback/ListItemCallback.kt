package com.hafhashtad.app.presentation.view.callback

import android.view.View
import com.hafhashtad.app.presentation.model.ListItem

interface ListItemCallback {

    fun onClick(item: ListItem.ProductListItem) {
        /* Default interface function */
    }

    fun onLongClick(view: View?, item: ListItem.ProductListItem): Boolean {
        /* Default interface function */
        return true
    }

    fun onClick(item: ListItem.CategoryListItem) {
        /* Default interface function */
    }

    fun onLongClick(view: View?, item: ListItem.CategoryListItem): Boolean {
        /* Default interface function */
        return true
    }

    fun onClick(item: ListItem.LoadingListItem) {
        /* Default interface function */
    }

    fun onLongClick(view: View?, item: ListItem.LoadingListItem): Boolean {
        /* Default interface function */
        return true
    }

    fun onClick(item: ListItem.ErrorListItem) {
        /* Default interface function */
    }

    fun onLongClick(view: View?, item: ListItem.ErrorListItem): Boolean {
        /* Default interface function */
        return true
    }
}

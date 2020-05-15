package com.hafhashtad.app.presentation.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hafhashtad.app.presentation.model.ListItem
import com.hafhashtad.app.presentation.model.ListItemType

class ListItemDistinguisher(
    private val oldSet: MutableList<ListItem>,
    private val newSet: MutableList<ListItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldSet.size
    }

    override fun getNewListSize(): Int {
        return newSet.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldSet[oldItemPosition]
        val newItem = newSet[newItemPosition]

        return if (oldItem.viewType == ListItemType.PRODUCT_ITEM &&
                   newItem.viewType == ListItemType.PRODUCT_ITEM
        ) {
            (newItem as ListItem.ProductListItem).product.id ==
                    (oldItem as ListItem.ProductListItem).product.id
        }
        else if (oldItem.viewType == ListItemType.CATEGORY_ITEM &&
                 newItem.viewType == ListItemType.CATEGORY_ITEM
        ) {
            (newItem as ListItem.CategoryListItem).category.id ==
                    (oldItem as ListItem.CategoryListItem).category.id
        }
        else {
            false
        }
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem = oldSet[oldItemPosition]
        val newItem = newSet[newItemPosition]

        return if (oldItem.viewType == ListItemType.PRODUCT_ITEM &&
                   newItem.viewType == ListItemType.PRODUCT_ITEM
        ) {
            return newItem == oldItem
        }
        else if (oldItem.viewType == ListItemType.CATEGORY_ITEM &&
                 newItem.viewType == ListItemType.CATEGORY_ITEM
        ) {
            return newItem == oldItem
        }
        else {
            false
        }
    }
}

package com.hafhashtad.core.domain

data class Product(
    val id: Int,
    val title: String,
    var categoryColor: String
) {

    override fun equals(obj: Any?): Boolean {
        if (obj == null) {
            return false
        }

        if (obj !is Product) {
            return false
        }

        if (obj === this) {
            return true
        }

        val stuff: Product = obj
        return id == stuff.id &&
               title == stuff.title &&
               categoryColor == stuff.categoryColor
    }
}

package com.hafhashtad.app.framework.network.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("categoryColor")
    val categoryColor: String
)

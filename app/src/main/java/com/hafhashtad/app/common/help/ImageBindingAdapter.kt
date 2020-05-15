package com.hafhashtad.app.common.help

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hafhashtad.app.R

/**
 * This is just a copy-paste class!!
 */

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("thumbnailImgUrl")
    fun setThumbnailImgUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load("https://my-json-server.typicode.com/YUSMLE/hafhashtad/products/pics/thumbs/$imageUrl")
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("originalImgUrl")
    fun setOriginalImgUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load("https://my-json-server.typicode.com/YUSMLE/hafhashtad/products/pics/$imageUrl")
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}

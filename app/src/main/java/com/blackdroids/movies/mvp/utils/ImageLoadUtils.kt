package com.blackdroids.movies.mvp.utils

import android.widget.ImageView
import com.blackdroids.movies.mvp.data.api.ServerGateway
import com.squareup.picasso.Picasso

object ImageLoadUtils {
    fun loadBigImage(imagePath: String, imageView: ImageView){
        Picasso.get().load(String.format("%sw780%s", ServerGateway.BASE_IMAGE_URL, imagePath)).into(imageView)
    }

    fun loadImage(imagePath: String, imageView: ImageView){
        Picasso.get().load(String.format("%sw342%s", ServerGateway.BASE_IMAGE_URL, imagePath)).into(imageView)
    }
}
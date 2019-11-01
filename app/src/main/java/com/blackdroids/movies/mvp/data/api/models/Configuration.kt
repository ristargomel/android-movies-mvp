package com.blackdroids.movies.mvp.data.api.models

import com.google.gson.annotations.SerializedName

data class Configuration(

    @SerializedName("images")
    val images: ImageConfiguration?,

    @SerializedName("change_keys")
    val changeKeys: List<String>?

)

data class ImageConfiguration(

    @SerializedName("base_url")
    val baseUrl: String?,

    @SerializedName("secure_base_url")
    val secureBaseUrl: String?,

    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>?,

    @SerializedName("logo_sizes")
    val logoSizes: List<String>?,

    @SerializedName("poster_sizes")
    val postersSizes: List<String>?,

    @SerializedName("profile_sizes")
    val profilesSizes: List<String>?,

    @SerializedName("still_sizes")
    val stillesSizes: List<String>?
)
package com.dawnstrive.pixallery.data.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<Images>,
)

data class Images(
    @SerializedName("id") val id: String,
    @SerializedName("largeImageURL") val largeImageUrl: String
)
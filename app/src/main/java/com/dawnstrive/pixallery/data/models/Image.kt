package com.dawnstrive.pixallery.data.models

data class Image(
    val hits: List<Images>
)

data class Images(
    val id: Int,
    val largeImageUrl: String,
)
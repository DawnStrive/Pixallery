package com.dawnstrive.pixallery.data.remote.apis

import com.dawnstrive.pixallery.data.models.Category
import com.dawnstrive.pixallery.data.models.Image
import com.dawnstrive.pixallery.utils.SecretI.YOUR_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/?key=${YOUR_API_KEY}&image_type=photo")
    suspend fun getImagesByCategory(
        @Query("category") category: String,
        @Query("orientation") orientation: String = "vertical",
    ): Response<Image>
}
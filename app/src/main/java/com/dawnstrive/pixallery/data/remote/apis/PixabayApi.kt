package com.dawnstrive.pixallery.data.remote.apis

import com.dawnstrive.pixallery.data.models.Category
import com.dawnstrive.pixallery.data.models.Image
import com.dawnstrive.pixallery.utils.SecretI.YOUR_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    suspend fun getImagesByCategory(
        @Query("key") key: String = YOUR_API_KEY,
        @Query("image_type") imageType: String = "photo",
        @Query("category") category: String,
        @Query("orientation") orientation: String = "vertical",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20,
    ): Response<Image>
}
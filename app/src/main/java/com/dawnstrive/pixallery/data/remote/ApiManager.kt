package com.dawnstrive.pixallery.data.remote

import com.dawnstrive.pixallery.utils.Consts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    fun getInstance() = Retrofit.Builder()
        .baseUrl(Consts.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
package com.dawnstrive.pixallery.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dawnstrive.pixallery.data.models.Category
import com.dawnstrive.pixallery.data.models.Images
import com.dawnstrive.pixallery.data.remote.ApiManager
import com.dawnstrive.pixallery.data.remote.ImagesPagingSource
import com.dawnstrive.pixallery.data.remote.apis.PixabayApi
import com.dawnstrive.pixallery.utils.Consts
import kotlinx.coroutines.flow.Flow

class ImagesRepository(
    private val apiService: PixabayApi = ApiManager.getInstance().create(PixabayApi::class.java),
) {

    fun letImagesFlow(
        pagingConfig: PagingConfig = getDefaultPageConfig(),
        category: String
    ): Flow<PagingData<Images>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { ImagesPagingSource(apiService, category) }
        ).flow
    }


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = Consts.DEFAULT_PAGE_INDEX, enablePlaceholders = false
        )
    }

}
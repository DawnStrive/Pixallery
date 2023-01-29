package com.dawnstrive.pixallery.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dawnstrive.pixallery.data.models.Image
import com.dawnstrive.pixallery.data.models.Images
import com.dawnstrive.pixallery.data.remote.apis.PixabayApi
import com.dawnstrive.pixallery.utils.Consts.DEFAULT_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException


class ImagesPagingSource(
    private val service: PixabayApi
) : PagingSource<Int, Images>() {
    override fun getRefreshKey(state: PagingState<Int, Images>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Images> {
        val pageIndex = params?.key ?: DEFAULT_PAGE_INDEX
        return try {
            val data = service.getImagesByCategory(category = "nature", page = pageIndex)
            var response = data.body()?.hits

            LoadResult.Page(
                response!!,
                prevKey = if (pageIndex == DEFAULT_PAGE_INDEX) null else pageIndex - 1,
                nextKey = if (response == null) null else pageIndex + 1
            )

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}
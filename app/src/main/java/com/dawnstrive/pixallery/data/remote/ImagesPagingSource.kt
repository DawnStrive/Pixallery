package com.dawnstrive.pixallery.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dawnstrive.pixallery.data.models.Image
import com.dawnstrive.pixallery.data.models.Images
import com.dawnstrive.pixallery.data.remote.apis.PixabayApi
import com.dawnstrive.pixallery.utils.Consts.DEFAULT_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException


class ImagesPagingSource(
    private val service: PixabayApi,
    private val category: String
) : PagingSource<Int, Images>() {
    override fun getRefreshKey(state: PagingState<Int, Images>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Images> {
        val pageIndex = params?.key ?: DEFAULT_PAGE_INDEX
        return try {
            Log.d("MyTag", category)
            val data = service.getImagesByCategory(category = category, page = pageIndex)
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
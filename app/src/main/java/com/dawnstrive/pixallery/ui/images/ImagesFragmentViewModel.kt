package com.dawnstrive.pixallery.ui.images

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.dawnstrive.pixallery.data.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImagesFragmentViewModel() : ViewModel() {

    private val repository: ImagesRepository

    init {
        repository = ImagesRepository()
    }

    fun fetchImages(category: String): Flow<PagingData<String>>? {

        Log.d("MyTag", category.toString() + " In Fragment")

        return repository?.letImagesFlow(category = category)
            ?.map { it.map { it.largeImageUrl } }
            ?.cachedIn(viewModelScope)
    }

}

class ImagesFragmentViewModelFactory(private val category: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImagesFragmentViewModel::class.java))
            return ImagesFragmentViewModel() as T
        else {
            throw java.lang.IllegalArgumentException("An unknown ViewModel class")
        }
    }
}

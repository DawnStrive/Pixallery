package com.dawnstrive.pixallery.ui.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.dawnstrive.pixallery.data.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ImagesFragmentViewModel : ViewModel() {

    private val repository: ImagesRepository = ImagesRepository()

    fun fetchImages(): Flow<PagingData<String>> {
        return repository.letImagesFlow()
            .map { it.map { it.largeImageUrl } }
            .cachedIn(viewModelScope)
    }
}
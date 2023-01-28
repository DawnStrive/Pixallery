package com.dawnstrive.pixallery.ui.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dawnstrive.pixallery.data.models.Category
import com.dawnstrive.pixallery.data.remote.ApiManager
import com.dawnstrive.pixallery.data.remote.apis.PixabayApi
import kotlinx.coroutines.launch

class CategoriesFragmentViewModel : ViewModel() {

    private val _categoryImage = MutableLiveData<List<Category>>()
    val categoryImage: LiveData<List<Category>> = _categoryImage

    fun loadCategoryImage(categoryNames: List<String>) {
        val categories = arrayListOf<Category>()
            categories.clear()

        viewModelScope.launch {
            val apiManager = ApiManager.getInstance().create(PixabayApi::class.java)

            for (i in 0 until categoryNames.size) {
                val data = apiManager.getImagesByCategory(categoryNames[i]).body()
                if (data?.hits != null) {
                    val image = data.hits[11].largeImageUrl // hits[custom number] just for images that will fit categories
                    val category = Category(
                        categoryNames[i], image
                    )
                    categories.add(category)
                }
            }
            _categoryImage.postValue(categories as List<Category>)
        }

    }
}
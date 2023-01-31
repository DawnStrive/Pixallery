package com.dawnstrive.pixallery.ui.images

import android.content.Context.WINDOW_SERVICE
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawnstrive.pixallery.R
import com.dawnstrive.pixallery.data.ImagesRepository
import com.dawnstrive.pixallery.data.models.Images
import com.dawnstrive.pixallery.data.remote.ApiManager
import com.dawnstrive.pixallery.databinding.FragmentImagesBinding
import com.dawnstrive.pixallery.databinding.ItemImageBinding
import com.dawnstrive.pixallery.ui.adapters.CategoriesAdapter
import com.dawnstrive.pixallery.ui.adapters.ImagesAdapter
import com.dawnstrive.pixallery.ui.adapters.LoadingStateAdapter
import com.dawnstrive.pixallery.utils.Consts
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ImagesFragment : Fragment(), ImagesAdapter.OnItemClickListener {

    private lateinit var bind: FragmentImagesBinding

    private val viewModel: ImagesFragmentViewModel by viewModels()

    private lateinit var adapter: ImagesAdapter

    private lateinit var loaderStateAdapter: LoadingStateAdapter

    private var chosenCategory = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        chosenCategory = arguments?.getString(Consts.ARGS_SELECTED_CATEGORY_TITLE) ?: "nature"

        bind = FragmentImagesBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        fetchImages()
    }

    private fun fetchImages() {
        lifecycleScope.launch {
            viewModel.fetchImages(chosenCategory)?.distinctUntilChanged()?.collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    private fun setupViews() {
        adapter = ImagesAdapter(this@ImagesFragment)
        loaderStateAdapter = LoadingStateAdapter { adapter.run { retry() } }
        bind.rvImages.adapter = adapter.withLoadStateFooter(loaderStateAdapter)
        bind.rvImages.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    override fun onClick(imageUrl: String) {
        val args = bundleOf(Consts.ARGS_SELECTED_IMAGE_URL to imageUrl)
        findNavController().navigate(R.id.currentImageFragment, args)
    }
}
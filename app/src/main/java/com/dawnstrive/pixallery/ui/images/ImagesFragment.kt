package com.dawnstrive.pixallery.ui.images

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawnstrive.pixallery.R
import com.dawnstrive.pixallery.data.models.Images
import com.dawnstrive.pixallery.databinding.FragmentImagesBinding
import com.dawnstrive.pixallery.databinding.ItemImageBinding
import com.dawnstrive.pixallery.ui.adapters.CategoriesAdapter
import com.dawnstrive.pixallery.ui.adapters.ImagesAdapter
import com.dawnstrive.pixallery.utils.Consts
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ImagesFragment : Fragment(), ImagesAdapter.OnItemClickListener {

    private lateinit var bind: FragmentImagesBinding

    private val viewModel: ImagesFragmentViewModel by viewModels()

    private var adapter: ImagesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            viewModel.fetchImages().distinctUntilChanged().collectLatest {
                adapter?.submitData(it)
            }
        }
    }

    private fun setupViews() {
        adapter = ImagesAdapter(this@ImagesFragment)
        bind.rvImages.adapter = adapter
        bind.rvImages.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    override fun onClick(imageUrl: String) {
        val args = bundleOf(Consts.ARGS_SELECTED_IMAGE_URL to imageUrl)
        findNavController().navigate(R.id.currentImageFragment, args)
    }
}
package com.dawnstrive.pixallery.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawnstrive.pixallery.R
import com.dawnstrive.pixallery.databinding.FragmentCategoriesBinding
import com.dawnstrive.pixallery.ui.adapters.CategoriesAdapter
import com.dawnstrive.pixallery.utils.Consts

class CategoriesFragment : Fragment(), CategoriesAdapter.OnItemClickListener {

    private lateinit var bind: FragmentCategoriesBinding
    private val viewModel: CategoriesFragmentViewModel by viewModels()

    private var adapter: CategoriesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        bind = FragmentCategoriesBinding.inflate(inflater, container, false)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categories = listOf(
            "animals", "travel", "buildings", "food", "education", "business"
        )

        viewModel.loadCategoryImage(categories)

        adapter = CategoriesAdapter(arrayListOf(), this)
        bind.rvCategories.adapter = adapter
        bind.rvCategories.layoutManager = LinearLayoutManager(requireContext())

        addObservers()
    }

    private fun addObservers() {
        viewModel.categoryImage.observe(viewLifecycleOwner) { categories ->
            adapter?.setCategories(categories)
        }
    }

    override fun onClick(category: String) {
        val bundle = bundleOf(Consts.ARGS_SELECTED_CATEGORY_TITLE to category)
        findNavController().navigate(R.id.imagesFragment, bundle)
    }

}
package com.dawnstrive.pixallery.ui.categories

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dawnstrive.pixallery.R
import com.dawnstrive.pixallery.databinding.FragmentCategoriesBinding
import com.dawnstrive.pixallery.ui.adapters.CategoriesAdapter

class CategoriesFragment : Fragment() {

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
//        val categories = resources.getStringArray(R.array.categories)
        val categories = listOf(
            "backgrounds", "travel", "buildings", "food", "education", "feelings", "sports"
        )

        viewModel.loadCategoryImage(categories)

        adapter = CategoriesAdapter(arrayListOf())
        bind.rvCategories.adapter = adapter
        bind.rvCategories.layoutManager = LinearLayoutManager(requireContext())

        addObservers()
    }

    private fun addObservers() {
        viewModel.categoryImage.observe(viewLifecycleOwner) { categories ->
            adapter?.setCategories(categories)
        }
    }

}
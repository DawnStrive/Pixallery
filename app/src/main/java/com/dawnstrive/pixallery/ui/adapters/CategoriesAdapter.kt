package com.dawnstrive.pixallery.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.dawnstrive.pixallery.data.models.Category
import com.dawnstrive.pixallery.databinding.ItemCategoryBinding
import com.dawnstrive.pixallery.utils.CategoryDiffCallback

class CategoriesAdapter(private var categories: ArrayList<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>() {

    fun setCategories(categories: List<Category>) {

        val diffCallback = CategoryDiffCallback(this.categories, categories)
        val diffResults = DiffUtil.calculateDiff(diffCallback)
        this.categories.clear()
        this.categories.addAll(categories)
        diffResults.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val bind = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CategoryHolder(bind)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val categoryPosition = categories[position]

        Glide.with(holder.itemView.context)
            .load(categoryPosition.imageUrl)
            .into(holder.bind.ivCategory)

        holder.bind.tvCategoryName.text = categoryPosition.title
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryHolder(val bind: ItemCategoryBinding) : ViewHolder(bind.root)


}
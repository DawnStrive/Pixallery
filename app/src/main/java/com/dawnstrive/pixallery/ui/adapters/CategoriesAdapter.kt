package com.dawnstrive.pixallery.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dawnstrive.pixallery.data.models.Category
import com.dawnstrive.pixallery.databinding.ItemCategoryBinding

class CategoriesAdapter(private var categories: List<Category>) :
    Adapter<CategoriesAdapter.CategoryHolder>() {

    fun setCategories(categories: List<Category>){
        Log.d("MyTag", categories.toString())
        this.categories = categories
        notifyDataSetChanged()
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
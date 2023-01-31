package com.dawnstrive.pixallery.ui.adapters

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dawnstrive.pixallery.R
import com.dawnstrive.pixallery.data.models.Images
import com.dawnstrive.pixallery.databinding.ItemCategoryBinding
import com.dawnstrive.pixallery.databinding.ItemImageBinding

class ImagesAdapter(private val onItemClickListener: OnItemClickListener) :
    PagingDataAdapter<String, ImagesAdapter.ImageHolder>(ImagesAdapter) {

    companion object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val bind = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ImageHolder(bind)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val imagePosition = getItem(position)

        Glide.with(holder.itemView.context)
            .load(imagePosition)
            .placeholder(R.drawable.placeholder)
            .into(holder.bind.ivImage)

        holder.itemView.setOnClickListener {
            if (imagePosition != null) {
                onItemClickListener.onClick(imagePosition)
            }
        }
    }

    inner class ImageHolder(val bind: ItemImageBinding) : RecyclerView.ViewHolder(bind.root)
    interface OnItemClickListener {
        fun onClick(imageUrl: String)
    }
}

package com.dawnstrive.pixallery.utils

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.dawnstrive.pixallery.data.models.Category

class CategoryDiffCallback(
    private val oldList: List<Category>,
    private val newList: List<Category>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (title, imageUrl) = oldList[oldItemPosition]
        val (title1, imageUrl1) = newList[newItemPosition]
        return title == title1 && imageUrl == imageUrl1
    }

    @Nullable
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]


        return oldItem == newItem
    }
}
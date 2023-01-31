package com.dawnstrive.pixallery.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dawnstrive.pixallery.databinding.ItemLoaderBinding

class LoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadingStateAdapter.LoadingHolder>() {

    override fun onBindViewHolder(holder: LoadingHolder, loadState: LoadState) {

        holder.bind.btnRetry.setOnClickListener {
            retry()
        }

        if (loadState is LoadState.Loading) {
            holder.bind.mlLoader.transitionToEnd()
        } else {
            holder.bind.mlLoader.transitionToStart()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingHolder {
        val bind = ItemLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingHolder(bind)
    }

    inner class LoadingHolder(val bind: ItemLoaderBinding) : ViewHolder(bind.root)
}

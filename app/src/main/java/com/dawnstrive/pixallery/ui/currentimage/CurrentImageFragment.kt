package com.dawnstrive.pixallery.ui.currentimage

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.dawnstrive.pixallery.databinding.FragmentCurrentImageBinding
import com.dawnstrive.pixallery.utils.Consts

class CurrentImageFragment : Fragment() {

    private lateinit var bind: FragmentCurrentImageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        bind = FragmentCurrentImageBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        val selectedImageUrl = arguments?.getString(Consts.ARGS_SELECTED_IMAGE_URL)

        var image: Bitmap? = null

        Glide.with(requireContext()).asBitmap().load(selectedImageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bind.ivSelectedImage.setImageBitmap(resource)
                    image = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        bind.btnSetWallpaper.setOnClickListener {
            val wallpaperManager = WallpaperManager.getInstance(requireContext())
            wallpaperManager.setBitmap(image)
        }
    }

    private fun setupViews() {
        val view = requireActivity().window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {


        } else {
            view.decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            )
        }

    }

}
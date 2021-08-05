package com.example.utilities

import android.content.Context
import android.graphics.drawable.Drawable


import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class Images {

    companion object{

         fun loadImageURL(request : RequestImage) {
             with(request){
                 Glide.with(context)
                     .load(Constants.IMAGE_URL + url)
                     .diskCacheStrategy(DiskCacheStrategy.ALL)
                     .fitCenter()
                     .override(request.width,request.height)
                     .priority(Priority.HIGH)
                     .listener(object : RequestListener<Drawable> {
                         override fun onLoadFailed(
                             e: GlideException?,
                             model: Any?,
                             target: Target<Drawable>?,
                             isFirstResource: Boolean
                         ): Boolean {
                             progressBar.visibility = View.INVISIBLE
                             imageView.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_image_not_load))
                             return isFirstResource
                         }

                         override fun onResourceReady(
                             resource: Drawable?,
                             model: Any?,
                             target: Target<Drawable>?,
                             dataSource: DataSource?,
                             isFirstResource: Boolean
                         ): Boolean {
                             progressBar.visibility = View.INVISIBLE
                             imageView.setImageDrawable(resource)
                             return isFirstResource
                         }
                     })
                     .into(imageView)
             }

        }
    }



}
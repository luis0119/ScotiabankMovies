package com.example.utilities

import android.content.Context
import android.widget.ImageView
import android.widget.ProgressBar

data class RequestImage(val progressBar: ProgressBar,
                        val imageView: ImageView,
                        val url: String?,
                        val context: Context,
                        val height: Int,
                        val width: Int)
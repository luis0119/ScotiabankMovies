package com.example.application.integration

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.utilities.Constants.POSITION_POPULAR
import com.example.utilities.Constants.POSITION_TOP_RATED
import com.example.utilities.Constants.POSITION_UPCOMING
import com.example.utilities.Constants.TYPE_POPULAR
import com.example.utilities.Constants.TYPE_TOP_RATED
import com.example.utilities.Constants.TYPE_UPCOMING
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MovieFactory @Inject constructor(
    private val movieMap: HashMap<Int, String>
) {

    init {
        movieMap[POSITION_TOP_RATED] = TYPE_TOP_RATED
        movieMap[POSITION_POPULAR] = TYPE_POPULAR
        movieMap[POSITION_UPCOMING] = TYPE_UPCOMING
    }


     fun getTypeMovie(position : Int) : Optional<String> {
        return Optional.ofNullable(movieMap[position])
     }

}
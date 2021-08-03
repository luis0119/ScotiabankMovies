package com.example.dataaccess.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dataaccess.db.MovieDB

@Dao
interface MovieDao {



    @Insert
    fun insertMovies(teams: List<MovieDB>)

    @Query("SELECT * FROM MovieDB  WHERE page = :page AND category = :category")
    fun getMovies(page : Int,category: String): List<MovieDB>

}
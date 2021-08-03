package com.example.dataaccess.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieDB {

    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name="adult")
    var adult : Boolean = false

    @ColumnInfo(name="backdropPath")
    var backdropPath : String = ""

    @ColumnInfo(name="id")
    var id : Int = 0

    @ColumnInfo(name="page")
    var page : Int = 0

    @ColumnInfo(name="originalLanguage")
    var originalLanguage : String = ""

    @ColumnInfo(name="originalTitle")
    var originalTitle : String = ""

    @ColumnInfo(name="overview")
    var overview : String = ""

    @ColumnInfo(name="popularity")
    var popularity : Double = 0.0

    @ColumnInfo(name="posterPath")
    var posterPath : String = ""

    @ColumnInfo(name="releaseDate")
    var releaseDate : String = ""

    @ColumnInfo(name="title")
    var title : String = ""

    @ColumnInfo(name="category")
    var category : String = ""

    @ColumnInfo(name="video")
    var video : Boolean = false

    @ColumnInfo(name="voteAverage")
    var voteAverage : Double = 0.0

    @ColumnInfo(name="voteCount")
    var voteCount : Int = 0

}
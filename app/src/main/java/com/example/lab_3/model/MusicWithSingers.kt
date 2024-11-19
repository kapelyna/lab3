package com.example.lab_3.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class MusicWithSingers(
    @ColumnInfo(name = "name")
    var musicName: String?,
    @ColumnInfo(name = "year")
    var year: String?,
    @ColumnInfo(name="album")
    var album: String?,
    @ColumnInfo(name = "id_music")
    var idMusic: Long?,
    @Embedded
    val singer: Singer?
)

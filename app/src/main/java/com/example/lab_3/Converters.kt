package com.example.lab_3

import androidx.room.TypeConverter
import com.example.lab_3.model.Album
import com.example.lab_3.model.Artist
import com.google.gson.Gson

class Converters {

    // Конвертер для перетворення Artist в String і назад
    @TypeConverter
    fun fromArtist(artist: Artist): String {
        return Gson().toJson(artist)
    }

    @TypeConverter
    fun toArtist(data: String): Artist {
        return Gson().fromJson(data, Artist::class.java)
    }

    // Конвертер для перетворення Album в String і назад
    @TypeConverter
    fun fromAlbum(album: Album): String {
        return Gson().toJson(album)
    }

    @TypeConverter
    fun toAlbum(data: String): Album {
        return Gson().fromJson(data, Album::class.java)
    }
}

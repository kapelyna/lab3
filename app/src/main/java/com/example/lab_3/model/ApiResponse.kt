package com.example.lab_3.model
import androidx.room.Entity
import androidx.room.PrimaryKey


data class ApiResponse(val data: List<Track>)
@Entity(tableName = "tracks")
data class Track(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val artist:Artist?,
    val album: Album?
)

data class Artist(
    val name: String
)

data class Album(
    val title: String
)
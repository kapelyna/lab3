package com.example.lab_3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "musics",
    foreignKeys = [
        ForeignKey(
            entity = Singer::class,
            parentColumns = ["id_singer"],
            childColumns = ["singerId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    //indices = [Index(value = ["singerId"])] // Додано індекс для колонки `singerId`
)
data class Music(
    @ColumnInfo(name = "singerId") var singerId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "album") val album: String?,
) : ItemTypeInterface {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_music")
    var id_music: Long = 0

    @Ignore
    var singer: Singer? = null

    @ColumnInfo(name = "id_singer")
    var idSinger: Long = singer?.idSinger ?: singerId

    override fun getType(): Int {
        return ItemTypeInterface.MUSIC_TYPE
    }
}

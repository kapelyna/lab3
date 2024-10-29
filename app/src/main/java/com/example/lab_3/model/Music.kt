package com.example.lab_3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "musics")
data class Music(
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="singerId") val singerId: Int,
    @ColumnInfo(name="year") val year: String,
    @ColumnInfo(name="album") val album: String,
):ItemTypeInterface{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    override fun getType(): Int {
        return ItemTypeInterface.MUSIC_TYPE
    }
}

//data class Music(
//    val name:String,
//    val singer:String,
//    val year:String,
//    val album:String
//) : ItemTypeInterface {
//     override fun getType(): Int{
//        return ItemTypeInterface.MUSIC_TYPE;
//    }
//}

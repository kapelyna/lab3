package com.example.lab_3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "singers")
data class Singer(
    @ColumnInfo(name = "singerName") val singerName : String,
    @ColumnInfo(name = "singerSurname") val singerSurname : String
):ItemTypeInterface{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_singer")
    var idSinger: Long =0

    override fun getType(): Int {
        return ItemTypeInterface.SINGER_TYPE
    }
}






//data class Singer(
//    val name:String,
//    val surname:String,
//) : ItemTypeInterface {
//    override fun getType(): Int{
//        return ItemTypeInterface.SINGER_TYPE;
//    }
//}
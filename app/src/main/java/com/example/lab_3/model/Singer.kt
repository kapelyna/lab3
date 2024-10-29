package com.example.lab_3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "singers")
data class Singer(
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "surname") val surname : String
):ItemTypeInterface{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
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
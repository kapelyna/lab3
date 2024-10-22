package com.example.lab_3.model

data class Music(
    val name:String,
    val singer:String,
    val year:String,
    val album:String
) : ItemTypeInterface {
     override fun getType(): Int{
        return ItemTypeInterface.MUSIC_TYPE;
    }
}

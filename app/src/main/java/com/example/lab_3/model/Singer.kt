package com.example.lab_3.model

data class Singer(
    val name:String,
    val surname:String,
) : ItemTypeInterface {
    override fun getType(): Int{
        return ItemTypeInterface.SINGER_TYPE;
    }
}
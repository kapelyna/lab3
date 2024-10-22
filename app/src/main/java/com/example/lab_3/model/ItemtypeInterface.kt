package com.example.lab_3.model

interface ItemTypeInterface {
    fun getType() : Int
    companion object{
        const val MUSIC_TYPE = 1
        const val SINGER_TYPE=2
    }
}
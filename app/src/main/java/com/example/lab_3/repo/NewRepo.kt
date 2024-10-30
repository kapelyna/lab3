package com.example.lab_3.repo

import androidx.lifecycle.LiveData
import com.example.lab_3.dao.NewDao
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer

class NewRepo(
    private val appDao: NewDao
) {

    suspend fun addMusic(music: Music) {
        appDao.insertMusic(music)
    }


    suspend fun addSinger(singer: Singer) {
        appDao.insertSinger(singer)
    }


    suspend fun getAllMusics(): List<Music> {
        return appDao.getListOfMusics()
    }


    suspend fun getAllSingers(): List<Singer> {
        return appDao.getListOfSingers()
    }


    fun getSingerById(singerId: Int): LiveData<Singer?> {
        return appDao.getSingerById(singerId)
    }
}

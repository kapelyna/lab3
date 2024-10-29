package com.example.lab_3.repo

import androidx.lifecycle.LiveData
import com.example.lab_3.dao.NewDao
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer

class NewRepo(
    private val appDao: NewDao
) {
    // Метод для додавання одного музичного треку
    suspend fun addMusic(music: Music) {
        appDao.insertMusic(music)
    }

    // Метод для додавання одного співака
    suspend fun addSinger(singer: Singer) {
        appDao.insertSinger(singer)
    }

    // Метод для отримання списку всіх музичних треків
    suspend fun getAllMusics(): List<Music> {
        return appDao.getListOfMusics()
    }

    // Метод для отримання списку всіх співаків
    suspend fun getAllSingers(): List<Singer> {
        return appDao.getListOfSingers()
    }

    // Метод для отримання співака за ID
    fun getSingerById(singerId: Int): LiveData<Singer?> {
        return appDao.getSingerById(singerId)
    }
}

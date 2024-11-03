package com.example.lab_3.repo

import androidx.lifecycle.LiveData
import com.example.lab_3.dao.NewDao
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewRepo(
    private val appDao: NewDao
) {

    suspend fun clearMusic() {
        withContext(Dispatchers.IO) {
            appDao.deleteAllMusics()
        }
    }

    suspend fun clearSingers() {
        withContext(Dispatchers.IO) {
            appDao.deleteAllSingers()
        }
    }

    suspend fun insertMusics(musics: List<Music>) {
        appDao.insertMusics(musics)
    }

    suspend fun insertSingers(singers: List<Singer>) {
        appDao.insertSingers(singers)
    }

    suspend fun updateMusics(music: Music) {
        appDao.updateMusic(music)
    }

    suspend fun addMusic(music: Music) {
        appDao.insertMusic(music)
    }

    suspend fun addSinger(singer: Singer) {
        appDao.insertSinger(singer)
    }

    suspend fun getFullMusic(): List<Music> {
        val musics: ArrayList<Music> = ArrayList()
        val musicWithSinger = appDao.getFullMusic()

        musicWithSinger.forEach {
            val music = Music( it.singer.idSinger.toLong(), it.musicName,it.year, it.album)
            music.singer = it.singer
            music.id_music = it.idMusic
            music.idSinger = it.singer.idSinger
            musics.add(music)
        }
        return musics
    }

    suspend fun delSinger(singer: Singer) {
        appDao.deleteSinger(singer)
    }

    suspend fun getAllMusics(): List<Music> {
        return appDao.getListOfMusics()
    }

    suspend fun getAllSingers(): List<Singer> {
        return appDao.getListOfSingers()
    }
}

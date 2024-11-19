package com.example.lab_3.repo

import com.example.lab_3.dao.NewDao
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer
import com.example.lab_3.model.Track
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
            val music = Music( it.singer?.idSinger!!.toLong(),
                it.musicName.toString(), it.year.toString(), it.album)
            music.singer = it.singer
            music.id_music = it.idMusic!!
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
    suspend fun getSingerByName(name: String): Singer? {
        return appDao.getSingerByName(name)
    }
    suspend fun getMusicByTitleAndArtist(title: String, singerId: Long, album: String): Music? {
        return appDao.getMusicByTitleAndArtist(title, singerId, album)
    }
    suspend fun saveDeezerData(tracks: List<Track>) {
        withContext(Dispatchers.IO) {
            tracks.forEach { track ->
                val singerNameParts = track.artist?.name?.split(" ", limit = 2)
                val singerName = singerNameParts?.getOrNull(0) ?: "Unknown"
                val singerSurname = singerNameParts?.getOrNull(1) ?: ""

                val singer = Singer(singerName = singerName, singerSurname = singerSurname)

                // Додаємо або перевіряємо існування виконавця
                val existingSinger = getAllSingers().find {
                    it.singerName == singerName && it.singerSurname == singerSurname
                }

                val singerId = if (existingSinger == null) {
                    addSinger(singer)
                    getAllSingers().find {
                        it.singerName == singerName && it.singerSurname == singerSurname
                    }?.idSinger ?: 0L
                } else {
                    existingSinger.idSinger
                }

                // Додаємо пісню
                val music = Music(
                    singerId = singerId,
                    name = track.title,
                    year = "2024", // Рік випуску з API
                    album = track.album?.title
                )
                addMusic(music)
            }
        }
    }
}

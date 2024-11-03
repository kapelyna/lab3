package com.example.lab_3.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lab_3.model.Music
import com.example.lab_3.model.MusicWithSingers
import com.example.lab_3.model.Singer

@Dao
interface NewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusics(musics:List<Music>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingers(singers:List<Singer>)

    @Query("SELECT * FROM musics")
    suspend fun getListOfMusics(): List<Music>

    @Query("SELECT * FROM singers")
    suspend fun getListOfSingers(): List<Singer>

    @Query("SELECT * FROM musics Join singers On musics.singerId = singers.id_singer")
    suspend fun getFullMusic(): List<MusicWithSingers>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(music: Music)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSinger(singer: Singer)

    @Update
    suspend fun updateMusic(music:Music)

    @Update
    suspend fun updateSinger(singer:Singer)

    @Delete
    suspend fun deleteMusic(music: Music)

    @Delete
    suspend fun deleteSinger(singer: Singer)

    @Query("Delete from musics")
    suspend fun deleteAllMusics()

    @Query("Delete from singers")
    suspend fun deleteAllSingers()


}
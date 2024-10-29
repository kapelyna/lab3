package com.example.lab_3.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer

@Dao
interface NewDao {
    @Query("SELECT * FROM musics")
    fun getListOfMusics(): List<Music>

    @Query("SELECT * FROM singers")
    fun getListOfSingers(): List<Singer>

    @Query("SELECT * FROM singers WHERE id = :singerId LIMIT 1")
    fun getSingerById(singerId: Int): LiveData<Singer?>

    @Insert
    fun insertMusic(music: Music)

    @Insert
    fun insertSinger(singer: Singer)

    @Delete
    fun deleteMusic(music: Music)

    @Delete
    fun deleteSinger(singer: Singer)


}
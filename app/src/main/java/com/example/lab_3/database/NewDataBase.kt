package com.example.lab_3.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lab_3.Converters
import com.example.lab_3.dao.NewDao
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer
import com.example.lab_3.model.Track

@Database(entities = [Singer::class, Music::class, Track::class], version =8, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NewDataBase : RoomDatabase(){
    abstract fun newDao() : NewDao

    companion object {
        @Volatile
        private var INSTANCE: NewDataBase?=null

        fun getDataBase(context: Context):NewDataBase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    NewDataBase::class.java,
                    "app_db.db3"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
        }
    }
}
}
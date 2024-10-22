package com.example.lab_3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab_3.model.ItemTypeInterface
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer

class MyViewModel(app: Application) : AndroidViewModel(app) {
    // Використовуємо public LiveData для спостереження
    val musics = MutableLiveData<List<ItemTypeInterface>>().apply {
        value = listOf(
            Music("Shape of you", "Ed Sheeran", "2016", "÷"),
            Singer("Miley", "Cyrus"),
            Music("Blinding Lights", "The Weeknd", "2019", "After Hours"),
            Music("Levitating", "Dua Lipa", "2020", "Future Nostalgia"),
            Music("Watermelon Sugar", "Harry Styles", "2019", "Fine Line"),
            Singer("Freddie", "Mercury"),
            Music("Shallow", "Lady Gaga & Bradley Cooper", "2018", "A Star Is Born"),
            Music("Old Town Road", "Lil Nas X", "2019", "7 EP"),
        )
    }

    fun addMusic() {
        val newMusic = Music("Music 1", "Singer 1", "1", "Album 1")
        // Додаємо нову музику до існуючого списку
        val updatedList = musics.value?.toMutableList() ?: mutableListOf()
        updatedList.add(newMusic)
        musics.postValue(updatedList)
    }
    fun addSinger() {
        val newSinger = Singer("Name 1", "Surname 1")
        // Додаємо нову музику до існуючого списку
        val updatedList = musics.value?.toMutableList() ?: mutableListOf()
        updatedList.add(newSinger)
        musics.postValue(updatedList)
    }
}

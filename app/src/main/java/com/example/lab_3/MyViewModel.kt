package com.example.lab_3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab_3.model.ItemTypeInterface
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer
import com.example.lab_3.repo.NewRepo
import kotlinx.coroutines.launch

class MyViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: NewRepo = (app as App).appRepo

    private var _myList: MutableLiveData<List<ItemTypeInterface>> =
        MutableLiveData<List<ItemTypeInterface>>().apply {
            value = emptyList()
        }
    val myList: LiveData<List<ItemTypeInterface>> = _myList

    private val singersList = listOf(
        Singer("Ed", "Sheeran"),
        Singer("Bruno", "Mars"),
        Singer("Taylor", "Swift"),
        Singer("Freddie", "Mercury"),
        Singer("Elton", "John"),
        Singer("Michael", "Jackson"),
        Singer("Whitney", "Houston"),
        Singer("Miley", "Cyrus")
    )
    private val musicList = listOf(
        Music(44, "Shape of You", "2017", "÷"),
        Music(45, "Uptown Funk", "2014", "Uptown Special"),
        Music(46, "Blank Space", "2014", "1989"),
        Music(47, "Bohemian Rhapsody", "1975", "A Night at the Opera"),
        Music(48, "Rocket Man", "1972", "Honky Château"),
        Music(49, "Thriller", "1982", "Thriller"),
        Music(50, "I Will Always Love You", "1992", "The Bodyguard Soundtrack"),
        Music(51, "Wrecking Ball", "2013", "Bangerz")
    )

    init {
        viewModelScope.launch {
            repository.clearMusic()  // Очистка музики для тесту
            var singersInDb = repository.getAllSingers()
            if (singersInDb.isEmpty()) {
                repository.insertSingers(singersList)
                singersInDb = repository.getAllSingers()  // Оновлення списку після вставки
            }

            // Переконайтеся, що всі `singerId` в `musicList` відповідають співакам у базі
            if (singersInDb.isNotEmpty()) {
                repository.insertMusics(musicList)
            }

            getLists()  // Оновлення списку після вставки
        }
    }

    private fun getLists() {
        viewModelScope.launch {
            val singers = repository.getAllSingers()
            val musics = repository.getFullMusic()

            _myList.value = singers + musics
        }
    }

    fun delSinger(singer: Singer) {
        viewModelScope.launch {
            repository.delSinger(singer)
            getLists()
        }
    }

    fun addSinger(singer: Singer) {
        viewModelScope.launch {
            repository.addSinger(singer)
            getLists() // Update list after adding
        }
    }


    fun addRandomMusic(musicName: String, year: String, album: String) {
        viewModelScope.launch {

            val singers = repository.getAllSingers()
            if (singers.isNotEmpty()) {
                val randomSinger = singers.random()
                val music = Music(randomSinger.idSinger, musicName, year, album)
                repository.addMusic(music)
                getLists()
            }
        }
    }
}


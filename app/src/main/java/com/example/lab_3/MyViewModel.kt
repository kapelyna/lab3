package com.example.lab_3

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab_3.api.retrofitClient
import com.example.lab_3.model.ItemTypeInterface
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer
import com.example.lab_3.model.Track
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
        Music(1, "Shape of You", "2017", "÷"),
        Music(2, "Uptown Funk", "2014", "Uptown Special"),
        Music(3, "Blank Space", "2014", "1989"),
        Music(4, "Bohemian Rhapsody", "1975", "A Night at the Opera"),
        Music(5, "Rocket Man", "1972", "Honky Château"),
        Music(6, "Thriller", "1982", "Thriller"),
        Music(7, "I Will Always Love You", "1992", "The Bodyguard Soundtrack"),
        Music(8, "Wrecking Ball", "2013", "Bangerz")
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

    fun searchTracks(query: String) {
        viewModelScope.launch {
            try {
                Log.d("MyViewModel", "Searching tracks with query: $query")
                val response = retrofitClient.retrofitService.searchTracks(query)

                // Виводимо отримані треки в лог для перевірки
                response.data.forEach {
                    Log.d("Track Info", "Title: ${it.title}, Artist: ${it.artist?.name}, Album: ${it.album?.title}")
                }

                fetchAndSaveDeezerData(response.data)
                getLists()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("MyViewModel", "Error searching tracks", e)
            }
        }
        getLists()
    }

    private suspend fun fetchAndSaveDeezerData(tracks: List<Track>) {
        for (track in tracks) {
            val artistName = track.artist?.name ?: "Unknown Artist"

            // Перевіряємо, чи існує співак
            val existingSinger = repository.getSingerByName(artistName)
            val singerId = if (existingSinger == null) {
                val newSinger = Singer(artistName, "-")
                repository.addSinger(newSinger) // Зберігаємо нового співака
                newSinger.idSinger
            } else {
                existingSinger.idSinger
            }

            // Перевіряємо, чи є цей трек в базі
            val existingMusic = repository.getMusicByTitleAndArtist(track.title, singerId, track.album?.title ?: "Unknown Album")
            if (existingMusic == null) {
                val music = Music(
                    singerId = singerId,
                    name = track.title,
                    year = "2024", // Можна динамічно отримати, якщо є у відповіді
                    album = track.album?.title ?: "Unknown Album"
                )
                repository.addMusic(music) // Додаємо трек у базу
            }
        }
    }


}


package com.example.lab_3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab_3.model.Music
import com.example.lab_3.model.Singer
import com.example.lab_3.repo.NewRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(app: Application) : AndroidViewModel(app) {


    private val repository: NewRepo = (app as App).appRepo

    val musics = MutableLiveData<List<Music>>()
    val singers = MutableLiveData<List<Singer>>()

    init {
        loadAllMusics()
        loadAllSingers()
    }

    private fun loadAllMusics() {
        viewModelScope.launch(Dispatchers.IO) {
            musics.postValue(repository.getAllMusics())
        }
    }

    private fun loadAllSingers() {
        viewModelScope.launch(Dispatchers.IO) {
            singers.postValue(repository.getAllSingers())
        }
    }

    fun addMusic(music: Music) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMusic(music)
            loadAllMusics()
        }
    }

    fun addSinger(singer: Singer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSinger(singer)
            loadAllSingers()
        }
    }
}

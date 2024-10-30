package com.example.lab_3

import android.app.Application
import com.example.lab_3.database.NewDataBase
import com.example.lab_3.repo.NewRepo

class App : Application() {

    private val appDataBase by lazy { NewDataBase.getDataBase(this) }
    val appRepo by lazy { NewRepo(appDataBase.newDao()) }
}

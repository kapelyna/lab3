package com.example.lab_3.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object retrofitClient {
    private const val BASE_URL = "https://api.deezer.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val retrofitService: myApi by lazy {
        retrofit.create(myApi::class.java)
    }
}

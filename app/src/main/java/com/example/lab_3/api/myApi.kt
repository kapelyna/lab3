package com.example.lab_3.api

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.lab_3.model.ApiResponse

interface myApi {
    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): ApiResponse
}
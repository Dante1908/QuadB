package com.example.quadb.Data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search/shows")
    suspend fun searchShows(
        @Query("q") query: String // Ensure this parameter is passed
    ): Response<List<Item>> // Replace ApiResponse with your data class
}

package com.example.quadb.ViewModel

import com.example.quadb.Data.ApiService
import com.example.quadb.Data.Item

class ItemRepository(private val apiService: ApiService) {
    suspend fun fetchShows(query: String): List<Item>? {
        if (query.isBlank()) {
            throw IllegalArgumentException("Query parameter 'q' cannot be empty")
        }

        val response = apiService.searchShows(query)
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw Exception("Error fetching items: ${response.errorBody()?.string()}")
        }
    }

}

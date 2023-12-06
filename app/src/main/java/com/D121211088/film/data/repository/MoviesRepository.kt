package com.D121211088.film.data.repository

import com.D121211088.film.data.response.GetMoviesResponse
import com.D121211088.film.data.source.remote.ApiService

class MoviesRepository(private val apiService: ApiService) {

    suspend fun getMovies(): GetMoviesResponse {
        return apiService.getMovies()
    }
}
package com.D121211088.film.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211088.film.data.models.Movie
import com.D121211088.film.data.repository.MoviesRepository
import com.D121211088.film.MyApplication
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val movies: List<Movie>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val moviesRepository: MoviesRepository): ViewModel() {

    // initial state
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getMovies() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = moviesRepository.getMovies()
            mainUiState = MainUiState.Success(result.results.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    // block yg prtama dipanggil ktika ini dibuka
    init {
        getMovies()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val moviesRepository = application.container.moviesRepository
                MainViewModel(moviesRepository)
            }
        }
    }
}
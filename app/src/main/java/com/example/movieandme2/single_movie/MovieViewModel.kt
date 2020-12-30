package com.example.movieandme2.single_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieandme2.data.repository.NetworkState
import com.example.movieandme2.data.value_objet.MoviesDetails
import io.reactivex.disposables.CompositeDisposable

class MovieViewModel(private val movieRepository: MovieDetailsRepository, movieId: Int): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MoviesDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
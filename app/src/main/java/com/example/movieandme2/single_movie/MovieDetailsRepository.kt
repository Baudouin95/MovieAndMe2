package com.example.movieandme2.single_movie

import androidx.lifecycle.LiveData
import com.example.movieandme2.data.api.TheMovieDBInterface
import com.example.movieandme2.data.repository.MovieDetailsNetworkSource
import com.example.movieandme2.data.repository.NetworkState
import com.example.movieandme2.data.value_objet.MoviesDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TheMovieDBInterface) {

    lateinit var movieDetailsNetworkSource: MovieDetailsNetworkSource

    fun fetchSingleMovieDetails(compositeDisposable: CompositeDisposable, movieId: Int): LiveData<MoviesDetails>{
        movieDetailsNetworkSource = MovieDetailsNetworkSource(apiService, compositeDisposable)
        movieDetailsNetworkSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkSource.downloadedMovieDetailsResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState>{
        return movieDetailsNetworkSource.networkState
    }
}
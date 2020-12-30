package com.example.movieandme2.presentation.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieandme2.data.repository.NetworkState
import com.example.movieandme2.data.value_objet.Movie
import io.reactivex.disposables.CompositeDisposable

class PopularMovieViewModel(private val movieRepository: MoviePageListRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePageList: LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePageList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean{
        return moviePageList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
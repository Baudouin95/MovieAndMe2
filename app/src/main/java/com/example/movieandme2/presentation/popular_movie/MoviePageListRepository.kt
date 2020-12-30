package com.example.movieandme2.presentation.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movieandme2.data.api.POST_PER_PAGE
import com.example.movieandme2.data.api.TheMovieDBInterface
import com.example.movieandme2.data.repository.MovieDataSource
import com.example.movieandme2.data.repository.MovieDataSourceFactory
import com.example.movieandme2.data.repository.NetworkState
import com.example.movieandme2.data.value_objet.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePageListRepository(private val apiService: TheMovieDBInterface) {

    lateinit var moviePageList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePageList(compositeDisposable: CompositeDisposable): LiveData<PagedList<Movie>>{
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePageList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePageList
    }

    fun getNetworkState(): LiveData<NetworkState>{
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState
        )
    }
}
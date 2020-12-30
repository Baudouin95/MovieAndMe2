package com.example.movieandme2.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.movieandme2.R
import com.example.movieandme2.data.api.POSTER_BASE_URL
import com.example.movieandme2.data.api.TheMovieDBClient
import com.example.movieandme2.data.api.TheMovieDBInterface
import com.example.movieandme2.data.repository.NetworkState
import com.example.movieandme2.data.value_objet.MoviesDetails
import com.example.movieandme2.single_movie.MovieDetailsRepository
import com.example.movieandme2.single_movie.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.text.NumberFormat
import java.util.*

class MovieDetail : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId: Int = intent.getIntExtra("id", 1)

        val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository = MovieDetailsRepository(apiService)

        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar.visibility = if(it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if(it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })

    }

    fun bindUI(it: MoviesDetails){
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviesPosterUrl: String = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviesPosterUrl)
            .into(iv_movie_poster)
    }

    private fun getViewModel(movieId: Int): MovieViewModel {
        return ViewModelProviders.of(this,  object: ViewModelProvider.Factory{
            override fun <T: ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(movieRepository, movieId) as T
            }
        })[MovieViewModel::class.java]
    }
}
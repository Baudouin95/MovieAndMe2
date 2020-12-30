package com.example.movieandme2.data.api

import com.example.movieandme2.data.value_objet.MovieResponse
import com.example.movieandme2.data.value_objet.MoviesDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMovieDBInterface {

    // https://api.themoviedb.org/3/movie/464052?api_key=adcee816cdaaafa0e15524c2519c10c0
    // https://api.themoviedb.org/3/movie/popular?api_key=adcee816cdaaafa0e15524c2519c10c0&page=1
    // https://api.themoviedb.org/3/

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MoviesDetails>
}
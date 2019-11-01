package com.blackdroids.movies.mvp.data

import com.blackdroids.movies.mvp.data.api.models.Configuration
import com.blackdroids.movies.mvp.data.api.models.MovieDetails
import com.blackdroids.movies.mvp.data.api.models.MoviesData
import io.reactivex.Single

interface Repository {

    fun getConfiguration(): Single<Configuration>

    fun getPopularMovies(page: Int): Single<MoviesData>

    fun getMovieDetails(movieId: Long): Single<MovieDetails>
}
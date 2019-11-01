package com.blackdroids.movies.mvp.data

import com.blackdroids.movies.mvp.data.api.ServerGateway
import com.blackdroids.movies.mvp.data.api.models.Configuration
import com.blackdroids.movies.mvp.data.api.models.MovieDetails
import com.blackdroids.movies.mvp.data.api.models.MoviesData
import io.reactivex.Single

class RepositoryImpl: Repository {

    override fun getConfiguration(): Single<Configuration> {
        return ServerGateway.api.getConfiguration()
    }

    override fun getPopularMovies(page: Int): Single<MoviesData>{
        return ServerGateway.api.getPopularMoviesList(page = page)
    }

    override fun getMovieDetails(movieId: Long): Single<MovieDetails> {
        return ServerGateway.api.getMovieDetails(movieId = movieId)
    }
}
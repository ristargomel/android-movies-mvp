package com.blackdroids.movies.mvp.presentation.views

import androidx.paging.PagedList
import com.blackdroids.movies.mvp.data.api.models.Movie

interface MoviesView: BaseView {

    fun onMoviesLoaded(movies: PagedList<Movie>)

}
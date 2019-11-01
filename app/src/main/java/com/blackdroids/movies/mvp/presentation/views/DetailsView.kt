package com.blackdroids.movies.mvp.presentation.views

import com.blackdroids.movies.mvp.data.api.models.MovieDetails

interface DetailsView: BaseView {

    fun onDetailsLoaded(movieDetails: MovieDetails)

}
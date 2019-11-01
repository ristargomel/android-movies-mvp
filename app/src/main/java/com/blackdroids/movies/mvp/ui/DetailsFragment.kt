package com.blackdroids.movies.mvp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.blackdroids.movies.mvp.R
import com.blackdroids.movies.mvp.data.api.models.MovieDetails
import com.blackdroids.movies.mvp.presentation.presenters.DetailsPresenter
import com.blackdroids.movies.mvp.presentation.views.DetailsView
import com.blackdroids.movies.mvp.utils.ImageLoadUtils
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class DetailsFragment : MvpAppCompatFragment(), DetailsView {


    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val args = DetailsFragmentArgs.fromBundle(arguments!!)
            val movie = args.movie
            movie?.let { presenter.loadDetails(it) }
        }

    }

    override fun onDetailsLoaded(movieDetails: MovieDetails) {
        movieDetails.backdropPath?.let { ImageLoadUtils.loadBigImage(it, imageViewBack) }
        movieDetails.posterPath?.let { ImageLoadUtils.loadImage(it, imageViewCover) }
        textViewTitle.text = movieDetails.title
        textViewAverage.text = movieDetails.voteAverage.toString()
        textViewBudget.text = String.format("Budget: $%d", movieDetails.budget)
        textViewOverView.text = movieDetails.overview
        textViewRuntime.text = String.format("%d min.", movieDetails.runtime)
    }

    override fun onLoad() {

    }

    override fun onError(error: Throwable) {

    }
}

package com.blackdroids.movies.mvp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList

import com.blackdroids.movies.mvp.R
import com.blackdroids.movies.mvp.data.api.models.Movie
import com.blackdroids.movies.mvp.presentation.presenters.MoviesPresenter
import com.blackdroids.movies.mvp.presentation.views.MoviesView
import com.blackdroids.movies.mvp.utils.gone
import com.blackdroids.movies.mvp.utils.visible
import kotlinx.android.synthetic.main.fragment_movies.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter


class MoviesFragment : MvpAppCompatFragment(),
    OnMovieClickListener, MoviesView {

    @InjectPresenter
    lateinit var presenter: MoviesPresenter

    private var mAdapter = MoviesPagedAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getMovies(this)
        recyclerView.adapter = mAdapter
    }

    override fun onMoviesLoaded(movies: PagedList<Movie>) {
        progressLoading.gone()
        textViewMessage.gone()
        buttonRetry.gone()
        mAdapter.submitList(movies)
    }

    override fun onLoad() {
        textViewMessage.gone()
        buttonRetry.gone()
    }

    override fun onError(error: Throwable) {
        progressLoading.gone()
        buttonRetry.visible()
        textViewMessage.visible()
    }

    override fun onMovieClick(movie: Movie) {
        val navController = findNavController()
        val actionMoviesListToMovieDetails = MoviesFragmentDirections
            .actionMoviesFragmentToDetailsFragment()
            .setMovie(movie)
        navController.navigate(actionMoviesListToMovieDetails)
    }

}

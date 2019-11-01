package com.blackdroids.movies.mvp.presentation.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.blackdroids.movies.mvp.data.RepositoryImpl
import com.blackdroids.movies.mvp.data.api.models.Movie
import com.blackdroids.movies.mvp.presentation.views.MoviesView
import com.blackdroids.movies.mvp.ui.movies.MoviesDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MoviesPresenter: MvpPresenter<MoviesView>(){

    private val mCompositeDisposable = CompositeDisposable()
    private val mRepository = RepositoryImpl()
    private var mMoviesPagedListLiveData: LiveData<PagedList<Movie>>? = null


    fun getMovies(owner: LifecycleOwner) {
        val moviesDataSourceFactory = MoviesDataSourceFactory(mCompositeDisposable, mRepository)
        val moviePagedListConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        mMoviesPagedListLiveData = LivePagedListBuilder(moviesDataSourceFactory, moviePagedListConfig).build()
        mMoviesPagedListLiveData!!.observe(owner, Observer { movies ->
            viewState.onMoviesLoaded(movies)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }
}
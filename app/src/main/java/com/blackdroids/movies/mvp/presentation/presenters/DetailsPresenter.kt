package com.blackdroids.movies.mvp.presentation.presenters

import com.blackdroids.movies.mvp.data.RepositoryImpl
import com.blackdroids.movies.mvp.data.api.models.Movie
import com.blackdroids.movies.mvp.presentation.views.DetailsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailsPresenter: MvpPresenter<DetailsView>() {

    private val mCompositeDisposable = CompositeDisposable()
    private val mRepository = RepositoryImpl()

    fun loadDetails(movie: Movie) {
        mCompositeDisposable.add(mRepository.getMovieDetails(
            movie.id
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieDetails ->
                viewState.onDetailsLoaded(movieDetails)
            }, { throwable ->
                viewState.onError(throwable)
            })
        )
    }

}
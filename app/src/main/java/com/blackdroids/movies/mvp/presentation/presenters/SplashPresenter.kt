package com.blackdroids.movies.mvp.presentation.presenters

import com.blackdroids.movies.mvp.data.RepositoryImpl
import com.blackdroids.movies.mvp.presentation.views.SplashView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>(){

    private val mCompositeDisposable = CompositeDisposable()
    private val mRepository = RepositoryImpl()

    fun loadConfiguration() {
        mCompositeDisposable.add(
            mRepository.getConfiguration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { config -> viewState.onConfigLoaded(config) },
                    { throwable -> viewState.onError(throwable) })
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }

}
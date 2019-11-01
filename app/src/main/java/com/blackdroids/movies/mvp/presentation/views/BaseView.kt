package com.blackdroids.movies.mvp.presentation.views

import moxy.MvpView

interface BaseView: MvpView {

    fun onLoad()

    fun onError(error: Throwable)

}
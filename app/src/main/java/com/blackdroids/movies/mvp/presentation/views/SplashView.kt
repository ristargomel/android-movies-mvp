package com.blackdroids.movies.mvp.presentation.views

import com.blackdroids.movies.mvp.data.api.models.Configuration
import moxy.MvpView

interface SplashView: BaseView {

    fun onConfigLoaded(configuration: Configuration)

}
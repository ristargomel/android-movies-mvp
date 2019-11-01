package com.blackdroids.movies.mvp.ui


import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

import com.blackdroids.movies.mvp.R
import com.blackdroids.movies.mvp.data.api.models.Configuration
import com.blackdroids.movies.mvp.presentation.presenters.SplashPresenter
import com.blackdroids.movies.mvp.presentation.views.SplashView
import com.blackdroids.movies.mvp.utils.gone
import com.blackdroids.movies.mvp.utils.visible
import kotlinx.android.synthetic.main.fragment_splash.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SplashFragment : MvpAppCompatFragment(), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    private val HANDLER_DELAY: Long = 3000

    private val mGoToNextFragmentHandler = Handler()
    private val mGoToNextFragmentRunnable = {
        findNavController().navigate(
            R.id.moviesFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(
                    R.id.splashFragment,
                    true
                ).build()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadConfiguration()

        buttonRetry.setOnClickListener {
            presenter.loadConfiguration()
        }
    }

    override fun onResume() {
        super.onResume()
        startHandler()
    }

    override fun onPause() {
        super.onPause()
        stopHandler()
    }

    private fun startHandler() {
        mGoToNextFragmentHandler.postDelayed(mGoToNextFragmentRunnable, HANDLER_DELAY)
    }

    private fun stopHandler() {
        mGoToNextFragmentHandler.removeCallbacks(mGoToNextFragmentRunnable)
        mGoToNextFragmentHandler.removeCallbacksAndMessages(null)
    }

    override fun onLoad() {
        Log.d(javaClass.simpleName, "onLoad...")
        textViewMessage.text = getString(R.string.loading)
        progressLoading.visible()
    }

    override fun onConfigLoaded(configuration: Configuration) {
        Log.d(javaClass.simpleName, "onConfigLoaded...")

        if (lifecycle.currentState === Lifecycle.State.RESUMED)
            startHandler()
    }

    override fun onError(error: Throwable) {
        Log.d(javaClass.simpleName, "onError...")
        progressLoading.gone()
        textViewMessage.text = error.message
        textViewMessage.visible()
        buttonRetry.visible()
    }
}

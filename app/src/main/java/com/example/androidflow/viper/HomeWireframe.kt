package com.example.androidflow.viper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object HomeWireframe {

    fun create(view: HomeActivity): HomePresenter {
        val presenter = HomePresenter()

        val interactor = HomeInteractor(Dispatchers.IO)
        val presenterScope = CoroutineScope(Dispatchers.Main)

        interactor.mOutput = presenter

        presenter.scope = presenterScope
        presenter.mInteractor = interactor
        presenter.mView = view

        return presenter
    }

}
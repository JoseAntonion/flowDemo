package com.example.androidflow.viper

import kotlinx.coroutines.flow.Flow

interface HomeContract {
    interface View
    interface Presenter {
        fun triggerLiveData()
        fun triggerStateFlow()
        fun getUser(): Flow<String>
        fun triggerShareFlow()
    }

    interface Interactor {
        fun getUserData(): Flow<String>
    }

    interface Router
    interface InteractorOutput
}
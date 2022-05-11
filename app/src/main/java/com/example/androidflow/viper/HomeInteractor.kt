package com.example.androidflow.viper

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

class HomeInteractor(override val coroutineContext: CoroutineContext) : CoroutineScope,
    HomeContract.Interactor {

    lateinit var mOutput: HomeContract.InteractorOutput

    override fun getUserData(): Flow<String> {
        return flow {
            repeat(10) {
                emit("Item $it")
                delay(1000)
            }
        }
    }

}
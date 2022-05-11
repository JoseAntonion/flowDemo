package com.example.androidflow.viper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomePresenter : HomeContract.InteractorOutput, HomeContract.Presenter {

    lateinit var mView: HomeActivity
    lateinit var mInteractor: HomeContract.Interactor
    lateinit var scope: CoroutineScope

    val firstFlow = flow {
        repeat(10) {
            emit("Item $it")
            delay(1000)
        }
    }

    private val _liveData = MutableLiveData("Inicio LiveData")
    val liveData: LiveData<String> = _liveData

    private val _stateFlow = MutableStateFlow("Inicio StateFlow")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    override fun triggerLiveData() {
        _liveData.value = "Live Data"
    }

    override fun triggerStateFlow() {
        _stateFlow.value = "State Flow"
    }

    override fun triggerShareFlow() {
        scope.launch {
            _stateFlow.emit("Shared Flow")
        }
    }

    override fun getUser(): Flow<String> {
        return mInteractor.getUserData()
    }


}
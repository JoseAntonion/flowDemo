package com.example.androidflow.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val firstFlow = flow {
        repeat(10) {
            emit("Item $it")
            delay(1000)
        }
    }

    private val _liveData = MutableLiveData("Inicio LiveData")
    val liveData = _liveData

    private val _stateFlow = MutableStateFlow("Inicio StateFlow")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun triggerLiveData() {
        _liveData.value = "Live Data"
    }

    fun triggerStateFlow() {
        _stateFlow.value = "State Flow"
    }

    fun triggerShareFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("Shared Flow")
        }
    }

    fun getUser(): Flow<String> {
        return firstFlow
    }

}
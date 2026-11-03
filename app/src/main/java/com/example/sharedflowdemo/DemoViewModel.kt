package com.example.sharedflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DemoViewModel : ViewModel() {
    private val _sharedFlow = MutableSharedFlow<Int>(
        replay = 1,
        extraBufferCapacity = 1
    )
    val sharedFlow: SharedFlow<Int> = _sharedFlow.asSharedFlow()

    init {
        startEmitting()
    }

    private fun startEmitting() {
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(2000)
                _sharedFlow.emit(i)
            }
        }
    }
}
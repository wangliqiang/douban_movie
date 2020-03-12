package com.app.douban_movie.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.douban_movie.data.remote.ApiStatus
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel() {

    // Loading状态处理
    val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    fun <T> request(
        onError: (t: Throwable) -> Unit = {},
        onExecute: suspend CoroutineScope.() -> T,
        onComplete: () -> Unit = {}
    ) {
        viewModelScope.launch(errorHandler { onError.invoke(it) }) {
            withContext(Dispatchers.Main) {
                try {
                    onExecute()
                }finally {
                    onComplete()
                }
            }
        }
    }

    private fun errorHandler(onError: (error: Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            onError.invoke(throwable)
        }
    }
}
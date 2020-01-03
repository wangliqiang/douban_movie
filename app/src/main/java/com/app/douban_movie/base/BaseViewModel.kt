package com.app.douban_movie.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.douban_movie.data.remote.ApiStatus
import kotlinx.coroutines.*
import timber.log.Timber

open class BaseViewModel : ViewModel() {

    // Loading状态处理
    val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    fun <T> request(
        onError: (t: Throwable) -> Unit = {},
        onExecute: suspend CoroutineScope.() -> T
    ) {
        viewModelScope.launch(errorHandler { onError.invoke(it) }) {
            withContext(Dispatchers.IO) {
                onExecute()
            }
        }
    }

    private fun errorHandler(onError: (error: Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            Timber.d(throwable)
            onError.invoke(throwable)
        }
    }
}
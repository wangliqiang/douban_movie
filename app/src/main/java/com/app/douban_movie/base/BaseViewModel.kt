package com.app.douban_movie.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.douban_movie_ktx.data.remote.ApiStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel : ViewModel() {

    private var viewModelJob = Job()

    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
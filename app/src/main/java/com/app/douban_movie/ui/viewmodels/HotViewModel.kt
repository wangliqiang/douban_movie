package com.app.douban_movie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.douban_movie.base.BaseViewModel
import com.app.douban_movie.data.MovieRepository
import com.app.douban_movie.data.model.Theaters
import com.app.douban_movie_ktx.data.remote.ApiStatus
import kotlinx.coroutines.launch

class HotViewModel constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private var _InTheaters: MutableLiveData<Theaters> = MutableLiveData()
    val inTheaters: LiveData<Theaters>
        get() = _InTheaters

    init {
        loading()
    }

    fun loading() {
        _status.value = ApiStatus.LOADING
        loadInTheaters()
    }

    fun refresh() {
        _status.value = ApiStatus.REFRESH
        loadInTheaters()
    }

    fun retry() {
        _status.value = ApiStatus.LOADING
        loadInTheaters()
    }

    private fun loadInTheaters() {
        coroutineScope.launch {
            try {
                movieRepository.loadIntheaters("济南", 0, 50).let {
                    _InTheaters.postValue(it)
                    _status.value = ApiStatus.DONE
                }
            } catch (ex: Exception) {
                _status.value = ApiStatus.ERROR
                _InTheaters.value = null
            }

        }
    }
}

package com.app.douban_movie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.douban_movie.base.BaseViewModel
import com.app.douban_movie.data.MovieRepository
import com.app.douban_movie.data.model.Theaters
import com.app.douban_movie.data.remote.ApiStatus
import kotlinx.coroutines.launch

class HotViewModel constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {


    private var _inTheaters: MutableLiveData<Theaters> = MutableLiveData()
    val inTheaters: LiveData<Theaters>
        get() = _inTheaters

    private var _comingSoon: MutableLiveData<Theaters> = MutableLiveData()
    val comingSoon: LiveData<Theaters>
        get() = _comingSoon

    fun inTheaterloading() {
        _status.value = ApiStatus.LOADING
        loadInTheaters()
    }

    fun inTheaterRefresh() {
        _status.value = ApiStatus.REFRESH
        loadInTheaters()
    }

    fun inTheaterRetry() {
        _status.value = ApiStatus.LOADING
        loadInTheaters()
    }

    fun comingSoonloading() {
        _status.value = ApiStatus.LOADING
        loadComingSoon()
    }

    fun comingSoonRefresh() {
        _status.value = ApiStatus.REFRESH
        loadComingSoon()
    }

    fun comingSoonRetry() {
        _status.value = ApiStatus.LOADING
        loadComingSoon()
    }


    private fun loadInTheaters() {
        coroutineScope.launch {
            try {
                movieRepository.loadIntheaters("济南", 0, 50).let {
                    _inTheaters.postValue(it)
                    _status.value = ApiStatus.DONE
                }
            } catch (ex: Exception) {
                _status.value = ApiStatus.ERROR
                _inTheaters.value = null
            }

        }
    }

    private fun loadComingSoon() {
        coroutineScope.launch {
            try {
                movieRepository.loadComingSoon("济南", 0, 50).let {
                    _comingSoon.postValue(it)
                    _status.value = ApiStatus.DONE
                }
            } catch (ex: Exception) {
                _status.value = ApiStatus.ERROR
                _comingSoon.value = null
            }

        }
    }
}

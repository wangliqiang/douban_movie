package com.app.douban_movie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.douban_movie.base.BaseViewModel
import com.app.douban_movie.data.MovieRepository
import com.app.douban_movie.data.model.Theaters
import kotlinx.coroutines.launch

class HotViewModel constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {

    private var _InTheaters: MutableLiveData<Theaters> = MutableLiveData()
    val inTheaters: LiveData<Theaters>
        get() = _InTheaters

    init {
        loadInTheaters()
    }

    fun loadInTheaters() {
        coroutineScope.launch {
            movieRepository.loadIntheaters("济南", 0, 1).let {
                _InTheaters.postValue(it)
            }
        }
    }
}

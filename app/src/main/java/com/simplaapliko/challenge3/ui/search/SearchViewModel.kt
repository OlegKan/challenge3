package com.simplaapliko.challenge3.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplaapliko.challenge3.domain.business.BusinessModel
import com.simplaapliko.challenge3.domain.business.BusinessSearchInteractor
import com.simplaapliko.challenge3.livedata.Wrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractor: BusinessSearchInteractor
) : ViewModel() {

    val searchResultPublisher: MutableLiveData<Wrapper<List<BusinessModel>>> = MutableLiveData()

    init {
        search()
    }

    private fun search() {
        viewModelScope.launch {
            searchInteractor.searchOpenBusinesses(
                query = "pizza, beer",
                latitude = 37.767016,
                longitude = -122.421842
            )
                .flowOn(Dispatchers.IO)
                .onStart { searchResultPublisher.postValue(Wrapper.loading()) }
                .map { list ->
                    if (list.isEmpty()) {
                        throw IllegalStateException("empty result")
                    }
                    list
                }
                .catch { onSearchError(it) }
                .collect { onSearchSuccess(it) }
        }
    }

    private fun onSearchSuccess(list: List<BusinessModel>) {
        if (list.isEmpty()) {
            searchResultPublisher.postValue(Wrapper.error(IllegalArgumentException("No upcoming trips")))
        } else {
            searchResultPublisher.postValue(Wrapper.success(list))
        }
    }

    private fun onSearchError(throwable: Throwable) {
        searchResultPublisher.postValue(Wrapper.error(throwable))
    }

    fun onBusinessSelected(model: BusinessModel) {
        //TODO show details
    }

    fun refresh() {
        search()
    }
}

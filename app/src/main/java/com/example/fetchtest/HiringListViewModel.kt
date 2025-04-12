package com.example.fetchtest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// TODO: Simple tests to ensure that hiringList is populated correctly
// TODO: Use DI to inject repository
// (I omitted these for the sake of completing the assignment)
class HiringListViewModel() : ViewModel() {
    private val repository = HiringDataRepository()
    private var _hiringList: MutableLiveData<List<HiringList>> = MutableLiveData()
    val hiringList: MutableLiveData<List<HiringList>>
        get() = _hiringList

    init {
        viewModelScope.launch {
            val latestList = repository.getHiringData()
            _hiringList.postValue(latestList)
        }
    }
}
package com.example.fetchtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class HiringItem(val id: Int, val listId: Int, val name: String)
data class HiringList(val id: Int, val items: List<HiringItem>)

class HiringListViewModel : ViewModel() {
    private val _hiringList = MutableLiveData<List<HiringList>>()
    val hiringList: LiveData<List<HiringList>> = _hiringList

    init {
        _hiringList.value = listOf(
            HiringList(1, listOf<Int>(1, 2, 3).map { HiringItem(it, 1, "Item $it") }),
            HiringList(2, listOf(4, 5, 6).map { HiringItem(it, 2, "Item $it") }),
            HiringList(3, listOf(7, 8, 9).map { HiringItem(it, 3, "Item $it") }),
        )
    }

}
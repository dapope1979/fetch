package com.example.fetchtest

data class HiringItem(val id: Int, val listId: Int, val name: String)

data class HiringList(val id: Int, val items: List<HiringItem>)
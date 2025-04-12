package com.example.fetchtest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.coroutines.executeAsync
import org.json.JSONArray

class HiringDataRepository {
    private val client = OkHttpClient()

    // Sort the JSON array into a list of HiringList objects,
    // each of which contains a list of HiringItem objects
    // TODO: Write tests for edge cases and happy case
    // (I omitted these for the sake of completing the assignment)
    fun sortJsonArrayIntoHiringList(jsonArray: JSONArray): List<HiringList> {
        val hiringList = mutableListOf<HiringList>()

        // Use a map to group items by listId
        val listIdMap = mutableMapOf<Int, MutableList<HiringItem>>()

        // Iterate through the JSON array and populate the map of listId's with it's children
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getInt("id")
            val listId = jsonObject.getInt("listId")

            // Skip null or empty names
            var name: String? = null
            try {
                name = jsonObject.getString("name")
            } catch (e: Exception) {
            }
            if (name == null || name == "null" || name.isEmpty()) {
                continue
            }

            // Use the map to group items by listId, so that we can tie each listId
            // to a header in the UI
            if (listId in listIdMap) {
                listIdMap[listId]?.add(HiringItem(id, listId, name))
            } else {
                listIdMap[listId] = mutableListOf(HiringItem(id, listId, name))
            }
        }

        // sort each list in map by item name and add to the final list
        for ((listId, list) in listIdMap) {
            list.sortBy { it.name }
            hiringList.add(HiringList(listId, list))
        }

        // return the final list sorted by listId
        return hiringList.sortedBy { it.id }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getHiringData(): List<HiringList> {
        // if more API calls or data from other sources becomes necessary,
        // consider moving this implementation to an API abstraction and a DataSource class

        var objectArray: JSONArray

        // Use OkHttp to get the JSON data from S3
        val request = Request.Builder()
            .url("https://fetch-hiring.s3.amazonaws.com/hiring.json")
            .build()

        val call = client.newCall(request)
        call.executeAsync().use { response ->
            withContext(Dispatchers.IO) {
                val responseBody = response.body.string()
                objectArray = JSONArray(responseBody)
            }
        }

        // sort the JSON array into a list of HiringList objects and return
        return sortJsonArrayIntoHiringList(objectArray)
    }
}
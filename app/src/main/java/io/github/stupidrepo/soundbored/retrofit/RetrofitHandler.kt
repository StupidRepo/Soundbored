package io.github.stupidrepo.soundbored.retrofit

import retrofit2.Call

interface APICallback<T> {
    fun onResponse(response: T?)
    fun onError(message: String?)
}

fun <T, R> fetchData(apiClient: T, getDataCall: T.(Map<String, String>) -> Call<R>, queryParams: Map<String, String>): R? {
    val call = apiClient.getDataCall(queryParams)
    val res = call.execute()

    return if (res.isSuccessful) {
        res.body()
    } else {
        throw Exception(res.errorBody()?.string() ?: "Failed to fetch data")
    }
}
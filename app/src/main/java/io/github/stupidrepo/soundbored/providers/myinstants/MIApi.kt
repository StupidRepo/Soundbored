package io.github.stupidrepo.soundbored.providers.myinstants

import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

// MyInstants API
@Serializable
data class Sound(
    val name: String,
    val soundURL: String,
)

@Serializable
data class MIResponse(
    val data: List<Sound>,
    val count: Int
)

interface MIApi {
    @GET("trending")
    fun getData(@QueryMap options: Map<String, String>): Call<MIResponse>

    @GET("search")
    fun search(@QueryMap options: Map<String, String>): Call<MIResponse>
}
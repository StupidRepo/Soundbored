package io.github.stupidrepo.soundbored.providers.sbw

import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

// SoundButtonsWorld API
@Serializable
data class Sound(
    val id: Int,
    val name: String,

    val color: String,

    val fileName: String,
)

@Serializable
data class SBWResponse(
    val data: List<Sound>,
    val total: Int
)

interface SBWApi {
    @GET("trends")
    fun getData(@QueryMap options: Map<String, String>): Call<SBWResponse>

    @GET("search")
    fun search(@QueryMap options: Map<String, String>): Call<SBWResponse>
}
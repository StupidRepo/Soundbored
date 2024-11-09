package io.github.stupidrepo.soundbored.retrofit.apis.soundbuttonsworld

import io.github.stupidrepo.soundbored.retrofit.GenericSound
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

// SoundButtonsWorld API
@Serializable
data class Sound(
    override val id: Int,
    override val name: String,

    override val color: String,

    val fileName: String,
    override val url: String,

    override val categoryId: Int,
    override val categoryName: String
): GenericSound

@Serializable
data class SBWResponse(
    val data: List<Sound>,
    val total: Int
)


interface SBWApi {
    @GET("trends")
    fun getData(@QueryMap options: Map<String, String>): Call<SBWResponse>
}
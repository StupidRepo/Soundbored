package io.github.stupidrepo.soundbored.providers.sbw

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SBWClient {
    private const val BASE_URL = "https://soundbuttonsworld.com/api/memes/"

    val instance: SBWApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SBWApi::class.java)
    }
}
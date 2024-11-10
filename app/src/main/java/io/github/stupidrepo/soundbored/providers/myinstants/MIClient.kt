package io.github.stupidrepo.soundbored.providers.myinstants

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MIClient {
    private const val BASE_URL = "https://api.doyouliveinthe.uk/myinstants/"

    val instance: MIApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MIApi::class.java)
    }
}
package io.github.stupidrepo.soundbored.providers.myinstants

import android.util.Log
import io.github.stupidrepo.soundbored.providers.IProvider
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import io.github.stupidrepo.soundbored.retrofit.fetchData
import kotlin.math.abs

class MIProvider : IProvider {
    override val isPaginated: Boolean = true

    private fun mapSounds(sounds: List<Sound>): List<GenericSound> {
        return sounds.map {
            GenericSound(
                id = abs(it.name.hashCode()),
                name = it.name,
                color = "gray",

                soundURL = it.soundURL,
                fileName = it.soundURL.split("/").last(),

                categoryId = null,
                categoryName = null
            )
        }
    }

    override fun getSounds(page: Int?, limit: Int?): List<GenericSound> {
        val queryParams = mapOf("page" to page.toString())

        val res = fetchData(MIClient.instance, { getData(it) }, queryParams)
        if (res != null) {
            return mapSounds(res.data)
        } else {
            throw Exception("Error when searching!")
        }
    }

    override fun getSound(id: Int): GenericSound {
        TODO("Not yet implemented")
    }

    override fun searchSounds(query: String, page: Int?, limit: Int?): List<GenericSound> {
        val queryParams =
            mapOf("page" to page.toString(), "query" to query)

        val res = fetchData(MIClient.instance, { search(it) }, queryParams)
        if (res != null) {
            return mapSounds(res.data)
        } else {
            throw Exception("Error when searching!")
        }
    }

    override fun getProviderURL(): String {
        return "https://myinstants.com"
    }

    override fun getProviderName(): String {
        return "MyInstants"
    }
}
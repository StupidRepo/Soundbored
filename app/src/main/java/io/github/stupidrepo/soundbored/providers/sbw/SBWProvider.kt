package io.github.stupidrepo.soundbored.providers.sbw

import io.github.stupidrepo.soundbored.providers.IProvider
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import io.github.stupidrepo.soundbored.retrofit.fetchData

class SBWProvider : IProvider {
    override val isPaginated: Boolean = true

    private fun mapSounds(sounds: List<Sound>): List<GenericSound> {
        return sounds.map {
            GenericSound(
               id = it.id,
                name = it.name,
                color = it.color,

                soundURL = "https://soundbuttonsworld.com/uploads/${it.fileName}",
                fileName = it.fileName,

                categoryId = null,
                categoryName = null
            )
        }
    }

    override fun getSounds(page: Int?, limit: Int?): List<GenericSound> {
        val queryParams = mapOf("page" to page.toString(), "pageSize" to limit.toString())

        val res = fetchData(SBWClient.instance, { getData(it) }, queryParams)
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
            mapOf("page" to page.toString(), "pageSize" to limit.toString(), "q" to query)

        val res = fetchData(SBWClient.instance, { search(it) }, queryParams)
        if (res != null) {
            return mapSounds(res.data)
        } else {
            throw Exception("Error when searching!")
        }
    }

    override fun getProviderURL(): String {
        return "https://soundbuttonsworld.com"
    }

    override fun getProviderName(): String {
        return "Sound Buttons World"
    }
}
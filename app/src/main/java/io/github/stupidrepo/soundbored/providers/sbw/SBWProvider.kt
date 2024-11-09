package io.github.stupidrepo.soundbored.providers.sbw

import io.github.stupidrepo.soundbored.providers.IProvider
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import io.github.stupidrepo.soundbored.retrofit.fetchData

class SBWProvider : IProvider {
    override val isPaginated: Boolean = true

    private fun mapSounds(sounds: List<Sound>): List<GenericSound> {
        return sounds.map {
            object : GenericSound {
                override val id: Int = it.id
                override val name: String = it.name
                override val color: String = it.color

                override val soundURL: String =
                    "https://soundbuttonsworld.com/uploads/${it.fileName}"
                override val fileName: String = it.fileName

                override val categoryId: Int? = null
                override val categoryName: String? = null
            }
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
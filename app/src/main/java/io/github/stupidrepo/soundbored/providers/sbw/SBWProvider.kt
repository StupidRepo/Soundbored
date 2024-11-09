package io.github.stupidrepo.soundbored.providers.sbw

import io.github.stupidrepo.soundbored.providers.IProvider
import io.github.stupidrepo.soundbored.retrofit.apis.soundbuttonsworld.SBWClient
import io.github.stupidrepo.soundbored.retrofit.apis.soundbuttonsworld.Sound
import io.github.stupidrepo.soundbored.retrofit.fetchData

class SBWProvider : IProvider<Sound> {
    override val isPaginated: Boolean = true

    override fun getSounds(page: Int?, limit: Int?): List<Sound> {
        val queryParams = mapOf("page" to page.toString(), "pageSize" to limit.toString())

        val res = fetchData(SBWClient.instance, { getData(it) }, queryParams)
        if(res != null) {
            return res.data
        } else {
            throw Exception("Error fetching data")
        }
    }

    override fun getSound(id: Int): Sound {
        TODO("Not yet implemented")
    }

    override fun getProviderURL(): String {
        return "https://soundbuttonsworld.com"
    }

    override fun getProviderName(): String {
        return "Sound Buttons World"
    }
}
package io.github.stupidrepo.soundbored.providers

import io.github.stupidrepo.soundbored.retrofit.GenericSound

interface IProvider {
    val isPaginated: Boolean

    fun getSounds(page: Int? = 1, limit: Int? = 40): List<GenericSound>
    fun getSound(id: Int): GenericSound

    fun searchSounds(query: String, page: Int? = 1, limit: Int? = 40): List<GenericSound>

    fun getProviderURL(): String
    fun getProviderName(): String
}
package io.github.stupidrepo.soundbored.providers

interface IProvider<T> {
    val isPaginated: Boolean

    fun getSounds(page: Int? = 1, limit: Int? = 40): List<T>
    fun getSound(id: Int): T

    fun getProviderURL(): String
    fun getProviderName(): String

    fun getUploadURL(): String {
        return "${getProviderURL()}/upload"
    }
}
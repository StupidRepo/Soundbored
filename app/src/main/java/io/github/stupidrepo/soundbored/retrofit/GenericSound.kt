package io.github.stupidrepo.soundbored.retrofit

interface GenericSound {
    val id: Int
    val name: String
    val color: String

    val soundURL: String
    val fileName: String

    val categoryId: Int?
    val categoryName: String?

    fun isFavourited(): Boolean {
        return false
    }
}
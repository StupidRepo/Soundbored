package io.github.stupidrepo.soundbored.retrofit

interface GenericSound {
    val id: Int
    val name: String
    val color: String

    val url: String

    val categoryId: Int?
    val categoryName: String
}
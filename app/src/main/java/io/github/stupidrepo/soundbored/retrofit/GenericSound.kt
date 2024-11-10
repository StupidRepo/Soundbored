package io.github.stupidrepo.soundbored.retrofit

import android.content.Context
import io.github.stupidrepo.soundbored.SoundboredApplication
import kotlinx.serialization.Serializable

@Serializable
data class GenericSound(
    val id: Int,
    val name: String,
    val color: String,

    val soundURL: String,
    val fileName: String,

    val categoryId: Int?,
    val categoryName: String?
)
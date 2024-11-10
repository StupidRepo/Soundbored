package io.github.stupidrepo.soundbored.handlers

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import io.github.stupidrepo.soundbored.JSON
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import kotlinx.serialization.encodeToString

class UserDataHandler(
    private val context: Context
) {
    val favourites = mutableStateListOf<GenericSound>()

    init {
        val sharedPref = context.getSharedPreferences("favourites", Context.MODE_PRIVATE)
        favourites.addAll(JSON.decodeFromString<List<GenericSound>>(sharedPref.getString("favourites", "[]") ?: "[]"))
    }

    fun addFavourite(sound: GenericSound) {
        val sharedPref = context.getSharedPreferences("favourites", Context.MODE_PRIVATE)

        favourites.add(sound)
        sharedPref.edit().putString("favourites", JSON.encodeToString(favourites.toList())).apply()
    }

    fun removeFavourite(sound: GenericSound) {
        val sharedPref = context.getSharedPreferences("favourites", Context.MODE_PRIVATE)

        favourites.remove(sound)
        sharedPref.edit().putString("favourites", JSON.encodeToString(favourites.toList())).apply()
    }

    fun isFavourite(sound: GenericSound): Boolean {
        return favourites.contains(sound)
    }
}
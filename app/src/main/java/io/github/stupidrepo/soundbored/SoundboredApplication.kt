package io.github.stupidrepo.soundbored

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import io.github.stupidrepo.soundbored.handlers.UserDataHandler
import io.github.stupidrepo.soundbored.providers.Providers
import kotlinx.serialization.json.Json

val JSON = Json {
    ignoreUnknownKeys = true
}

val currentProvider = mutableStateOf(Providers.providers[0])

class SoundboredApplication: Application() {
    val userDataHandler by lazy { UserDataHandler(this) }
}
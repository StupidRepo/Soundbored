package io.github.stupidrepo.soundbored.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.github.stupidrepo.soundbored.SoundboredApplication
import io.github.stupidrepo.soundbored.handlers.play
import io.github.stupidrepo.soundbored.ui.components.BodyTitleText
import io.github.stupidrepo.soundbored.ui.components.SoundboardGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen() {
    val ap = (LocalContext.current.applicationContext) as SoundboredApplication

    SoundboardGrid(sounds = ap.userDataHandler.favourites, title = "Favourites", onBottomReached = { }) {
        play(it, ap)
    }
}

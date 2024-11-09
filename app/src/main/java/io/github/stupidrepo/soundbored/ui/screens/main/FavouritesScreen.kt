package io.github.stupidrepo.soundbored.ui.screens.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import io.github.stupidrepo.soundbored.ui.components.BodyTitleText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen() {
    val ctx = LocalContext.current

    BodyTitleText("Favs screen!")
}
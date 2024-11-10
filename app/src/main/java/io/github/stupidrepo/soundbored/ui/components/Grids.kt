package io.github.stupidrepo.soundbored.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.stupidrepo.soundbored.retrofit.GenericSound

@Composable
fun SoundboardGrid(sounds: List<GenericSound>, onBottomReached: () -> Unit, onPlay: (GenericSound) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(sounds) { _, sound ->
            SoundboardCard(sound = sound, onExpand = {}, onPlay = {
                onPlay(sound)
            })
        }
    }
}
package io.github.stupidrepo.soundbored.ui.components

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun SoundboardGrid(sounds: List<GenericSound>, title: String?, onBottomReached: () -> Unit, onPlay: (GenericSound) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        if(title != null) {
            item {
                BodyTitleText(title)
            }
            item {} // FIXME: This is a hack to make the title show up all on one line :(
        }
        itemsIndexed(sounds) { index, sound ->
            SoundboardCard(sound = sound, onExpand = {}, onPlay = {
                onPlay(sound)
            })
        }
    }
}
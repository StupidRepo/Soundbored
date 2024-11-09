package io.github.stupidrepo.soundbored.ui.screens.main

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import io.github.stupidrepo.soundbored.play
import io.github.stupidrepo.soundbored.providers.Providers
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import io.github.stupidrepo.soundbored.ui.components.SoundboardCard
import io.github.stupidrepo.soundbored.ui.components.SoundboardGrid
import io.github.stupidrepo.soundbored.ui.components.TitleText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL

private var isRefreshing = mutableStateOf(false)
private var sounds = mutableStateListOf<GenericSound>()

private var provider = mutableStateOf(Providers.providers[0])

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundboardScreen() {
    val TAG = "SoundboardScreen-Fetch"

    val ctx = LocalContext.current
    val state = rememberPullToRefreshState()

    fun fetch() {
        isRefreshing.value = true

        sounds.clear()
        sounds.addAll(provider.value.getSounds())

        isRefreshing.value = false
    }

    LaunchedEffect(true) {
        CoroutineScope(Dispatchers.IO).launch {
            fetch()
        }
    }

    PullToRefreshBox(isRefreshing = isRefreshing.value, onRefresh = {
        CoroutineScope(Dispatchers.IO).launch {
            fetch()
        }
    }, state = state, modifier = Modifier.fillMaxSize()) {
        SoundboardGrid(sounds = sounds, title = "Popular Sounds", onBottomReached = {}) {
            play(it, ctx)
        }
    }
}
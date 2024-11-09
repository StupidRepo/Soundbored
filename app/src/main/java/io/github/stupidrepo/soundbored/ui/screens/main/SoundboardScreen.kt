package io.github.stupidrepo.soundbored.ui.screens.main

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import io.github.stupidrepo.soundbored.handlers.play
import io.github.stupidrepo.soundbored.providers.Providers
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import io.github.stupidrepo.soundbored.ui.components.SoundboardGrid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        try {
            sounds.clear()
            sounds.addAll(provider.value.getSounds())
        } catch (e: Exception) {
            Log.e(TAG, "Failed to fetch sounds: $e")
        }

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
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.stupidrepo.soundbored.currentProvider
import io.github.stupidrepo.soundbored.handlers.play
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import io.github.stupidrepo.soundbored.ui.components.SoundboardGrid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private var isRefreshing = mutableStateOf(false)
private var sounds = mutableStateListOf<GenericSound>()

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
            sounds.addAll(currentProvider.value.getSounds())

            isRefreshing.value = false
        } catch (e: Exception) {
            ctx.mainExecutor.execute {
                MaterialAlertDialogBuilder(ctx)
                    .setTitle("Error")
                    .setMessage("Failed to fetch sounds: $e")
                    .setPositiveButton("OK") { _, _ ->
                        run {
                            isRefreshing.value = false
                        }
                    }
                    .show()
            }
            Log.e(TAG, "Failed to fetch sounds: $e")
        }
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
        SoundboardGrid(sounds = sounds, onBottomReached = {}) {
            play(it, ctx)
        }
    }
}
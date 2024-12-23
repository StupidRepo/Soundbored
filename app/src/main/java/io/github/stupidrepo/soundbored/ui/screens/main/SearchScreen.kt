package io.github.stupidrepo.soundbored.ui.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.github.stupidrepo.soundbored.currentProvider
import io.github.stupidrepo.soundbored.handlers.play
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import io.github.stupidrepo.soundbored.ui.components.SoundboardGrid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    val TAG = "SearchScreen"

    val ctx = LocalContext.current
    var sounds by remember { mutableStateOf(listOf<GenericSound>()) }

    var searchQuery by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            keyboardActions = KeyboardActions(
                onSearch = {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            sounds = currentProvider.value.searchSounds(searchQuery)
                        } catch (e: Exception) {
                            Log.e(TAG, "Failed to search for sounds: $e")
                        }
                    }
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.width(8.dp))

        SoundboardGrid(sounds, {}) {
            play(it, ctx)
        }
    }
}
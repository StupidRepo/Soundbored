package io.github.stupidrepo.soundbored.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.stupidrepo.soundbored.retrofit.GenericSound
import io.github.stupidrepo.soundbored.retrofit.apis.soundbuttonsworld.Sound

@Composable
fun OurCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    OutlinedCard (
        modifier = modifier.padding(4.dp).height(164.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            content()
        }
    }
}

@Composable
fun SoundboardCard(sound: GenericSound, onExpand: (GenericSound) -> Unit, onPlay: () -> Unit) {
    OurCard(
        modifier = Modifier.clickable(onClick = { onExpand(sound) }).fillMaxWidth()
    ) {
        // make sound icon, with tint according the sound colour
        var tint = when (sound.color) {
            "red" -> Color.Red
            "blue" -> Color.Blue
            "green" -> Color.Green
            "yellow" -> Color.Yellow
            "purple" -> Color.Magenta
            "pink" -> Color.Magenta
//            "orange" -> Color.Yellow
//            "brown" -> Color.Gray
//            "grey" -> Color.Gray
//            "black" -> Color.Gray
            "white" -> Color.White
            else -> Color.Gray
        }
        IconButton(onClick = onPlay, modifier = Modifier.fillMaxSize(0.6f).weight(1f).padding(8.dp)) {
            Icon(
                imageVector = Icons.Rounded.PlayCircle,
                contentDescription = "Play Sound Icon",
                tint = tint,
                modifier = Modifier.fillMaxSize()
            )
        }

        // make sound title
        Text(sound.name, overflow = TextOverflow.Ellipsis, maxLines = 2, textAlign = TextAlign.Center)
    }
}

@Composable
@Preview
fun SoundboardCardPreview() {
    SoundboardCard(
        sound = Sound(1, "VINE BOOM VINE BOOM VINE BOOM VINE BOOM VINE BOOM VINE BOOM VINE", "red", "file1", "url1", 1, "Category 1"),
        onExpand = {},
    ) {}
}
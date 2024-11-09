package io.github.stupidrepo.soundbored.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import io.github.stupidrepo.soundbored.providers.sbw.Sound

fun getColour(name: String): Color = when(name) {
    "red" -> Color.Red
    "blue" -> Color.Blue
    "green" -> Color.Green
    "yellow" -> Color.Yellow
    "purple" -> Color.Magenta
    "pink" -> Color.Magenta
//            "orange" -> Color.Yellow
//            "brown" -> Color.Gray
//            "black" -> Color.Gray
    "white" -> Color.White
    else -> Color.Gray
}

@Composable
fun OurCard(modifier: Modifier = Modifier, borderStroke: BorderStroke, content: @Composable ColumnScope.() -> Unit) {
    OutlinedCard (
        modifier = modifier
            .padding(4.dp).fillMaxSize(),
        border = borderStroke
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

// Card
@Composable
fun SoundboardCard(sound: GenericSound, onExpand: (GenericSound) -> Unit, onPlay: () -> Unit) {
    OurCard(
        modifier = Modifier
            .clickable(onClick = { onExpand(sound) })
            .fillMaxWidth(),
        borderStroke = BorderStroke(2.dp, getColour(sound.color))
    ) {
        // make sound icon, with tint according the sound colour
        IconButton(onClick = onPlay, modifier = Modifier
            .size(118.dp)
            .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.PlayCircle,
                contentDescription = "Play Sound Icon",
                tint = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.fillMaxSize()
            )
        }

        // make sound title
        Text(sound.name, overflow = TextOverflow.Ellipsis, maxLines = 2, textAlign = TextAlign.Center)
    }
}

// Compact
//@Composable
//fun SoundboardCard(sound: GenericSound, onExpand: (GenericSound) -> Unit, onPlay: () -> Unit) {
//    OurCard(
//        modifier = Modifier
//            .clickable(onClick = { onExpand(sound) })
//            .fillMaxWidth().wrapContentHeight(),
//        borderStroke = BorderStroke(2.dp, getColour(sound.color))
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            // make sound icon, with tint according the sound colour
//            IconButton(onClick = onPlay, modifier = Modifier.size(48.dp)) {
//                Icon(
//                    imageVector = Icons.Rounded.PlayCircle,
//                    contentDescription = "Play Sound Icon",
//                    tint = MaterialTheme.colorScheme.inverseSurface,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//
//            // make sound title
//            Text(
//                sound.name.lowercase(),
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 2,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.weight(1f).padding(4.dp)
//            )
//
//            IconButton(onClick = {}, modifier = Modifier.size(28.dp)) {
//                Icon(
//                    imageVector = if(sound.isFavourited()) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
//                    contentDescription = "Favourite Icon",
//                    tint = MaterialTheme.colorScheme.inverseSurface,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//        }
//    }
//}


@Composable
@Preview
fun SoundboardCardPreview() {
    SoundboardCard(
        sound = object : GenericSound {
            override val id: Int = 318
            override val name: String = "Gangster's paradise Gangster's paradise Gangster's paradise Gangster's paradise PARADISE"
            override val color: String = "red"

            override val soundURL: String = "https://soundbuttonsworld.com/upload/ced71b4a-4f58-4ae3-95fb-34dcc5935631.mp3"
            override val fileName: String = "ced71b4a-4f58-4ae3-95fb-34dcc5935631.mp3"

            override val categoryId: Int? = null
            override val categoryName: String? = null
        },
        onExpand = {},
    ) {}
}
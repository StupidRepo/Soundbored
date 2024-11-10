package io.github.stupidrepo.soundbored.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.stupidrepo.soundbored.SoundboredApplication
import io.github.stupidrepo.soundbored.providers.IProvider
import io.github.stupidrepo.soundbored.retrofit.GenericSound

fun getColour(name: String): Color = when(name) {
    "red" -> Color.hsv(0f, 0.8f, 0.8f)
    "blue" -> Color.hsv(240f, 0.8f, 0.8f)
    "green" -> Color.hsv(120f, 0.8f, 0.8f)
    "yellow" -> Color.hsv(60f, 0.8f, 0.8f)
    "purple" -> Color.hsv(300f, 0.8f, 0.8f)
    "pink" -> Color.hsv(330f, 0.8f, 0.8f)
    "orange" -> Color.hsv(30f, 0.8f, 0.8f)
    "brown" -> Color.hsv(30f, 0.8f, 0.4f)
    "black" -> Color.hsv(0f, 0f, .35f)
    "white" -> Color.hsv(0f, 0f, 1f)
    else -> Color.Gray
}

@Composable
fun OurCard(modifier: Modifier = Modifier, borderStroke: BorderStroke, content: @Composable ColumnScope.() -> Unit) {
    OutlinedCard (
        modifier = modifier
            .padding(4.dp),
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
    val ap = (LocalContext.current.applicationContext) as SoundboredApplication

    OurCard(
        modifier = Modifier
            .clickable(onClick = { onExpand(sound) })
            .fillMaxWidth().fillMaxSize(),
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

        Spacer(modifier = Modifier.height(8.dp))

        IconButton(onClick = {
            if(ap.userDataHandler.isFavourite(sound)) {
                ap.userDataHandler.removeFavourite(sound)
            } else {
                ap.userDataHandler.addFavourite(sound)
            }
        }, modifier = Modifier.size(28.dp)) {
            Icon(
                imageVector = if(ap.userDataHandler.isFavourite(sound)) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                contentDescription = "Favourite Icon",
                tint = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

// Compact
@OptIn(ExperimentalTextApi::class)
@Composable
fun ProviderCard(provider: IProvider, isChecked: Boolean, onSelect: () -> Unit) {
    OutlinedCard (
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Checkbox(checked = isChecked, onCheckedChange = { onSelect() })

            Column {
                Text(
                    provider.getProviderName(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )

                Text(
                    AnnotatedString.Builder().apply {
                        withLink(
                            LinkAnnotation.Url(
                                provider.getProviderURL(),
                                TextLinkStyles(style = SpanStyle(color = Color.Cyan))
                            )
                        ) {
                            append(provider.getProviderURL())
                        }

                    }.toAnnotatedString(),
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = typography.bodySmall
                )
            }
        }
    }
}


@Composable
@Preview
fun SoundboardCardPreview() {
    SoundboardCard(
        sound = GenericSound(
            id = 318,
            name = "Gangster's paradise Gangster's paradise Gangster's paradise Gangster's paradise PARADISE",
            color = "red",

            soundURL = "https://soundbuttonsworld.com/upload/ced71b4a-4f58-4ae3-95fb-34dcc5935631.mp3",
            fileName = "ced71b4a-4f58-4ae3-95fb-34dcc5935631.mp3",

            categoryId = null,
            categoryName = null
        ),
        onExpand = {},
    ) {}
}
package stupidrepo.fridamanager.ui.screens.intro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import stupidrepo.fridamanager.ui.components.TitleText

@Composable
fun WelcomeScreen(
    isLoading: MutableState<Boolean>,
    modifier: Modifier,
    onRequestRoot: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(4.dp)
    ) {
        Column(Modifier.weight(2f)) {
            TitleText("Welcome!")
            Text("Welcome to Frida Manager.")
        }

        Column {
            Text("Before you get started, please tap the button below to request root access.")
            Button(onClick = onRequestRoot, modifier = Modifier.fillMaxWidth()) {
                if(isLoading.value) CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.size(24.dp)
                ) else Text("Request Root")
            }
        }
    }
}
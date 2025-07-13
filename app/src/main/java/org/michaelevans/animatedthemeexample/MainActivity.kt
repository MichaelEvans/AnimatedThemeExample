package org.michaelevans.animatedthemeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.michaelevans.animatedthemeexample.ui.theme.AnimatedTheme
import org.michaelevans.animatedthemeexample.ui.theme.PlainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // UI state for the sample
            var dark by remember { mutableStateOf(false) }
            var animated by remember { mutableStateOf(false) }

            if (animated) {
                AnimatedTheme(darkTheme = dark) {
                    AppContent(
                        dark = dark,
                        animated = animated,
                        onToggleTheme = { dark = !dark },
                        onToggleMode = { animated = !animated }
                    )
                }
            } else {
                PlainTheme(darkTheme = dark) {
                    AppContent(
                        dark = dark,
                        animated = animated,
                        onToggleTheme = { dark = !dark },
                        onToggleMode = { animated = !animated }
                    )
                }
            }
        }
    }
}

@Composable
fun AppContent(
    dark: Boolean,
    animated: Boolean,
    onToggleTheme: () -> Unit,
    onToggleMode: () -> Unit
) {
    Scaffold(Modifier.fillMaxSize()) { contentPadding ->
        Column(Modifier
            .padding(contentPadding)
            .padding(16.dp)) {
            Text("Current theme: ${if (dark) "Dark" else "Light"}")
            Text("Mode: ${if (animated) "Animated" else "Instant"}")

            Button(onClick = onToggleTheme, Modifier.padding(top = 16.dp)) {
                Text("Toggle theme")
            }
            Button(onClick = onToggleMode, Modifier.padding(top = 8.dp)) {
                Text("Toggle animation mode")
            }
        }
    }
}
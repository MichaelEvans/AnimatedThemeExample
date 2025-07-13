package org.michaelevans.animatedthemeexample.ui.theme

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PlainTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val scheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(colorScheme = scheme, content = content)
}

@Composable
fun AnimatedTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
    val targetScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val transition = updateTransition(targetScheme, label = "themeTransition")

    val primary by transition.animateColor(
        label = "primary",
        transitionSpec = { defaultColorTransitionSpec() }) { it.primary }
    val background by transition.animateColor(
        label = "background",
        transitionSpec = { defaultColorTransitionSpec() }) { it.background }
    val surface by transition.animateColor(
        label = "surface",
        transitionSpec = { defaultColorTransitionSpec() }) { it.surface }
    val onPrimary by transition.animateColor(
        label = "onPrimary",
        transitionSpec = { defaultColorTransitionSpec() }) { it.onPrimary }

    val animatedScheme = targetScheme.copy(
        primary = primary,
        background = background,
        surface = surface,
        onPrimary = onPrimary
    )
    MaterialTheme(colorScheme = animatedScheme, content = content)
}

private fun defaultColorTransitionSpec(): FiniteAnimationSpec<Color> =
    tween(durationMillis = 500, easing = LinearOutSlowInEasing)
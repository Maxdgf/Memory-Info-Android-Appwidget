package com.maxdgf.meminfo.mem_info_glance_appwidget

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.glance.material3.ColorProviders

internal object MemInfoWidgetTheme {
    val colors = ColorProviders(
        light = lightColorScheme(
            surface = Color.White,
            onSurface = Color.DarkGray,
            onBackground = Color.White,

            // RAM data progress bar colors
            secondary = Color(0xFF00AFFF),
            secondaryContainer = Color(0x800093D0),

            // Storage memory data progress bar colors
            tertiary = Color(0xFF00D9FF),
            tertiaryContainer = Color(0x8000A9C0)
        ),
        dark = darkColorScheme(
            surface = Color(0xFF282828),
            onSurface = Color.Gray,
            onBackground = Color.White,

            // RAM data progress bar colors
            secondary = Color(0xFF018ECB),
            secondaryContainer = Color(0x800077A6),

            // Storage memory data progress bar colors
            tertiary = Color(0xFF00A4C2),
            tertiaryContainer = Color(0x8000B8D0)
        )
    )
}
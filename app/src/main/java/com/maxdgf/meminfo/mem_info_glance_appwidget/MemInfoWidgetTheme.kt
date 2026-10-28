package com.maxdgf.meminfo.mem_info_glance_appwidget

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.glance.material3.ColorProviders

/* Warning!
* In the latest stable version (October 2026), Glance 1.2.0, the 'ColorProvider'
* constructor overload that accepts a 'Color' object is marked with the '@RestrictedApi'
* annotation (the same annotation also applies to the overload that accepts a color resource ID).
* Consequently, colors for progress bars currently stored within the color theme slots
* of the Glance widget. */

internal object MemInfoWidgetTheme {
    val colors = ColorProviders(
        light = lightColorScheme(
            surface = Color.White,
            onSurface = Color.DarkGray,
            onBackground = Color.White,

            // RAM data progress bar colors
            secondary = Color(0xFF00AFFF), // track color
            secondaryContainer = Color(0x800093D0), // background color

            // Storage memory data progress bar colors
            tertiary = Color(0xFF00D9FF), // track color
            tertiaryContainer = Color(0x8000A9C0) // background color
        ),
        dark = darkColorScheme(
            surface = Color(0xFF282828),
            onSurface = Color.Gray,
            onBackground = Color.White,

            // RAM data progress bar colors
            secondary = Color(0xFF018ECB), // track color
            secondaryContainer = Color(0x800077A6), // background color

            // Storage memory data progress bar colors
            tertiary = Color(0xFF00A4C2), // track color
            tertiaryContainer = Color(0x8000B8D0) // background color
        )
    )
}
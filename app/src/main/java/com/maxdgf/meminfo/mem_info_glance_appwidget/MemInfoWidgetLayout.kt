package com.maxdgf.meminfo.mem_info_glance_appwidget

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.LinearProgressIndicator
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.unit.ColorProvider
import com.maxdgf.meminfo.R

@Composable
private fun MemoryDataProgressbar(
    totalMemoryGb: Float,
    usedMemoryGb: Float,
    @DrawableRes iconResId: Int,
    caption: String,
    progressColor: ColorProvider,
    progressBackgroundColor: ColorProvider
) {
    Row(
        modifier = GlanceModifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            provider = ImageProvider(iconResId),
            contentDescription = "$caption memory data icon",
            modifier = GlanceModifier.size(25.dp),
            colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurface)
        )

        Spacer(modifier = GlanceModifier.width(10.dp))

        Box(
            modifier = GlanceModifier.defaultWeight(),
            contentAlignment = Alignment.Center
        ) {
            LinearProgressIndicator(
                progress = (usedMemoryGb * 1.0f) / totalMemoryGb,
                modifier = GlanceModifier.fillMaxWidth(),
                color = progressColor,
                backgroundColor = progressBackgroundColor
            )

            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
            ) {
                Text(
                    text = "$caption ${"%.1f".format((usedMemoryGb * 100) / totalMemoryGb)}%",
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = GlanceTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = GlanceModifier.defaultWeight())

                Text(
                    text = "${"%.1f".format(usedMemoryGb)}/${"%.1f".format(totalMemoryGb)} GB",
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = GlanceTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@SuppressLint("RestrictedApi")
@Composable
internal fun MemInfoWidgetLayout(
    androidVersion: String,
    sdkVersion: Int,
    deviceModelName: String,
    deviceManufacturer: String,
    totalStorageGb: Float,
    usedStorageGb: Float,
    totalRamGb: Float,
    usedRamGb: Float
) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(colorProvider = GlanceTheme.colors.surface)
            .padding(10.dp)
    ) {
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                provider = ImageProvider(R.drawable.baseline_phone_android_24),
                contentDescription = "Phone data icon",
                modifier = GlanceModifier.size(25.dp),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurface)
            )

            Spacer(modifier = GlanceModifier.width(10.dp))

            Box(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .background(GlanceTheme.colors.onSurface)
                    .cornerRadius(6.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Android $androidVersion, SDK $sdkVersion, $deviceModelName ($deviceManufacturer)",
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = GlanceTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Spacer(modifier = GlanceModifier.height(7.dp))

        MemoryDataProgressbar(
            totalMemoryGb = totalStorageGb,
            usedMemoryGb = usedStorageGb,
            iconResId = R.drawable.outline_storage_24,
            caption = "Storage",
            progressColor = GlanceTheme.colors.tertiary,
            progressBackgroundColor = GlanceTheme.colors.tertiaryContainer
        )

        Spacer(modifier = GlanceModifier.height(7.dp))

        MemoryDataProgressbar(
            totalMemoryGb = totalRamGb,
            usedMemoryGb = usedRamGb,
            iconResId = R.drawable.outline_memory_alt_24,
            caption = "RAM",
            progressColor = GlanceTheme.colors.secondary,
            progressBackgroundColor = GlanceTheme.colors.secondaryContainer
        )
    }
}
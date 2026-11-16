package com.maxdgf.meminfo


import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.assertHasContentDescription
import androidx.glance.testing.unit.assertHasText
import androidx.glance.testing.unit.hasTestTag
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import com.maxdgf.meminfo.mem_info_glance_appwidget.MemInfoWidgetLayout


@RunWith(AndroidJUnit4::class)
class MemInfoAppWidgetTest {
    @Test
    fun testMemInfoGlanceAppWidgetComposable() = runGlanceAppWidgetUnitTest {
        // Set the composable to test
        provideComposable {
            MemInfoWidgetLayout(
                androidVersion = "14",
                sdkVersion = 34,
                deviceModelName = "Some-Model",
                deviceManufacturer = "Brand",
                totalStorageGb = 256.0f,
                usedStorageGb = 156.5f,
                totalRamGb = 7.3f,
                usedRamGb = 5.1f,
                lowMemoryMode = true
            )
        }

        // Perform assertions

        // Device data row
        onNode(hasTestTag("device_data"))
            .assertHasText("Android 14, SDK 34, Some-Model (Brand)")

        // Storage data progressbar row elements
        onNode(hasTestTag("Storage_icon"))
            .assertHasContentDescription("Storage memory data icon")

        onNode(hasTestTag("Storage_total_GB"))
            .assertHasText("Storage 61.1%")

        onNode(hasTestTag("Storage_used_GB"))
            .assertHasText("156.5/256.0 GB")

        // RAM data progressbar row elements
        onNode(hasTestTag("RAM(low memory mode)_icon"))
            .assertHasContentDescription("RAM(low memory mode) memory data icon")

        onNode(hasTestTag("RAM(low memory mode)_total_GB"))
            .assertHasText("RAM(low memory mode) 69.9%")

        onNode(hasTestTag("RAM(low memory mode)_used_GB"))
            .assertHasText("5.1/7.3 GB")
    }
}
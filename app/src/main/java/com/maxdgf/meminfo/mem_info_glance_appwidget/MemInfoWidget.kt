package com.maxdgf.meminfo.mem_info_glance_appwidget


import android.content.Context

import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent


internal class MemInfoWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val deviceMemInfoManager = DeviceMemInfoManager()

            val ramData = deviceMemInfoManager.getRamData(context)
            val storageMemData = deviceMemInfoManager.getStorageMemoryData(context)
            val deviceData = deviceMemInfoManager.getDeviceData()

            GlanceTheme(colors = MemInfoWidgetTheme.colors) {
                MemInfoWidgetLayout(
                    androidVersion = deviceData.androidVersion,
                    sdkVersion = deviceData.sdkVersion,
                    deviceModelName = deviceData.modelName,
                    deviceManufacturer = deviceData.manufacturer,
                    totalStorageGb = storageMemData.totalStorageGb,
                    usedStorageGb = storageMemData.run { totalStorageGb - availableStorageGb },
                    totalRamGb = ramData.totalRamGb,
                    usedRamGb = ramData.run { totalRamGb - availableRamGb }
                )
            }
        }
    }
}
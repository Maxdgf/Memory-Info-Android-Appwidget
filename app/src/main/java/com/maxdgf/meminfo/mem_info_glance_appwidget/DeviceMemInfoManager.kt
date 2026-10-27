package com.maxdgf.meminfo.mem_info_glance_appwidget


import android.app.ActivityManager
import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import android.os.storage.StorageManager

import com.maxdgf.meminfo.mem_info_glance_appwidget.models.Device
import com.maxdgf.meminfo.mem_info_glance_appwidget.models.Ram
import com.maxdgf.meminfo.mem_info_glance_appwidget.models.StorageMemory


internal class DeviceMemInfoManager {
    private fun Long.toGb() = this.toFloat() / (1024 * 1024 * 1024)

    fun getRamData(context: Context): Ram {
        val activityManager = context.getSystemService(ActivityManager::class.java) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        return Ram(
            totalRamGb = memoryInfo.totalMem.toGb(),
            availableRamGb = memoryInfo.availMem.toGb()
        )
    }

    fun getStorageMemoryData(context: Context): StorageMemory {
        val storageStatsManager = context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager

        return StorageMemory(
            totalStorageGb = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT).toGb(),
            availableStorageGb = storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT).toGb()
        )
    }

    fun getDeviceData(): Device = Device(
        androidVersion = Build.VERSION.RELEASE,
        sdkVersion = Build.VERSION.SDK_INT,
        modelName = Build.MODEL,
        manufacturer = Build.MANUFACTURER
    )
}
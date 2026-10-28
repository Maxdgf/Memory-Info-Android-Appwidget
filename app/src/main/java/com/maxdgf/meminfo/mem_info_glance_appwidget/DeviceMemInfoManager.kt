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
    /** Converts bytes to gigabytes.
     * @return gigabytes */
    private fun Long.toGb() = this.toFloat() / (1024 * 1024 * 1024)

    /** Returns information about device's RAM.
     * @param context context
     * @return device's RAM data */
    fun getRamData(context: Context): Ram {
        val activityManager = context.getSystemService(ActivityManager::class.java) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        return Ram(
            totalRamGb = memoryInfo.totalMem.toGb(),
            availableRamGb = memoryInfo.availMem.toGb()
        )
    }

    /** Returns information about device's storage memory.
     * @param context context
     * @return device's storage memory data */
    fun getStorageMemoryData(context: Context): StorageMemory {
        val storageStatsManager = context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager

        return StorageMemory(
            totalStorageGb = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT).toGb(),
            availableStorageGb = storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT).toGb()
        )
    }

    /** Returns information about device.
     * @return device's data */
    fun getDeviceData(): Device = Device(
        androidVersion = Build.VERSION.RELEASE,
        sdkVersion = Build.VERSION.SDK_INT,
        modelName = Build.MODEL,
        manufacturer = Build.MANUFACTURER
    )
}
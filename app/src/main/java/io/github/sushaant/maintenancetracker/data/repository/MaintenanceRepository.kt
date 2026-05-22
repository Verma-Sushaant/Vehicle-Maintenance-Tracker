package io.github.sushaant.maintenancetracker.data.repository

import androidx.compose.runtime.mutableStateListOf
import io.github.sushaant.maintenancetracker.domain.dummy_data.MaintenanceData
import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry

object MaintenanceRepository {

    private val maintenanceEntries =
        mutableStateListOf<MaintenanceEntry>().apply {

            addAll(MaintenanceData.maintenanceEntries)
        }

    fun getMaintenanceEntries(vehicleId: Int): List<MaintenanceEntry> {

        return maintenanceEntries.filter {
            it.vehicleId == vehicleId
        }
    }

    fun addMaintenance(entry: MaintenanceEntry) {

        maintenanceEntries.add(entry)
    }
}
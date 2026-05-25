package io.github.sushaant.maintenancetracker.data.repository

import io.github.sushaant.maintenancetracker.domain.dummy_data.MaintenanceData
import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object MaintenanceRepository {

    private val _maintenanceEntries =
        MutableStateFlow(MaintenanceData.maintenanceEntries)

    val maintenanceEntries =
        _maintenanceEntries.asStateFlow()

    fun getMaintenanceEntries(vehicleId: Int): List<MaintenanceEntry> {

        return _maintenanceEntries.value.filter {
            it.vehicleId == vehicleId
        }
    }

    fun addMaintenance(entry: MaintenanceEntry) {

        _maintenanceEntries.update { currentEntries ->
            currentEntries + entry
        }
    }
}
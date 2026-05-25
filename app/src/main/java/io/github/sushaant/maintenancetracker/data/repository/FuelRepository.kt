package io.github.sushaant.maintenancetracker.data.repository

import io.github.sushaant.maintenancetracker.domain.dummy_data.FuelData
import io.github.sushaant.maintenancetracker.domain.model.FuelEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object FuelRepository {

    private val _fuelEntries =
        MutableStateFlow(FuelData.fuelEntries)

    val fuelEntries =
        _fuelEntries.asStateFlow()

    fun getFuelEntries(vehicleId: Int): List<FuelEntry> {

        return _fuelEntries.value.filter {
            it.vehicleId == vehicleId
        }
    }

    fun addFuelEntry(entry: FuelEntry) {

        _fuelEntries.update { currentEntries ->

            currentEntries + entry
        }
    }

    fun deleteFuelEntries(vehicleId: Int) {

        _fuelEntries.update { entries ->

            entries.filterNot {
                it.vehicleId == vehicleId
            }
        }
    }
}
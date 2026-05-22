package io.github.sushaant.maintenancetracker.data.repository

import androidx.compose.runtime.mutableStateListOf
import io.github.sushaant.maintenancetracker.domain.dummy_data.FuelData
import io.github.sushaant.maintenancetracker.domain.model.FuelEntry

object FuelRepository {

    private val fuelEntries =
        mutableStateListOf<FuelEntry>().apply {

            addAll(FuelData.fuelEntries)
        }

    fun getFuelEntries(vehicleId: Int): List<FuelEntry> {

        return fuelEntries.filter {
            it.vehicleId == vehicleId
        }
    }

    fun addFuelEntry(entry: FuelEntry) {

        fuelEntries.add(entry)
    }
}
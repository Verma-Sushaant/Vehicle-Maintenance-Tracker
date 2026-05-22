package io.github.sushaant.maintenancetracker.data.repository

import androidx.compose.runtime.mutableStateListOf
import io.github.sushaant.maintenancetracker.domain.dummy_data.VehicleData
import io.github.sushaant.maintenancetracker.domain.model.Vehicle

object VehicleRepository {

    private val vehicles =
        mutableStateListOf<Vehicle>().apply {

            addAll(VehicleData.vehicles)
        }

    fun getVehicles(): List<Vehicle> {

        return vehicles
    }

    fun getVehicleById(id: Int): Vehicle? {

        return vehicles.firstOrNull {
            it.id == id
        }
    }

    fun addVehicle(vehicle: Vehicle) {

        vehicles.add(vehicle)
    }
}
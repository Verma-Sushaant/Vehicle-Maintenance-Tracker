package io.github.sushaant.maintenancetracker.data.repository

import androidx.compose.runtime.mutableStateListOf
import io.github.sushaant.maintenancetracker.domain.dummy_data.VehicleData
import io.github.sushaant.maintenancetracker.domain.model.Vehicle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object VehicleRepository {

    private val _vehicles = MutableStateFlow(VehicleData.vehicles)

    val vehicles = _vehicles.asStateFlow()

    fun getVehicles(): List<Vehicle> {

        return _vehicles.value
    }

    fun getVehicleById(id: Int): Vehicle? {

        return _vehicles.value.firstOrNull {
            it.id == id
        }
    }

    fun addVehicle(vehicle: Vehicle) {

        _vehicles.update { currentVehicles ->
            currentVehicles + vehicle
        }
    }

    fun deleteVehicle(vehicleId: Int) {

        _vehicles.update { vehicles ->

            vehicles.filterNot {
                it.id == vehicleId
            }
        }
    }
}
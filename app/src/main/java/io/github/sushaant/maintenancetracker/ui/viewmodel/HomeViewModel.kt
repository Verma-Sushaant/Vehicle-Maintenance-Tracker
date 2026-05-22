package io.github.sushaant.maintenancetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.sushaant.maintenancetracker.data.repository.VehicleRepository
import io.github.sushaant.maintenancetracker.domain.model.Vehicle
import io.github.sushaant.maintenancetracker.ui.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(

            HomeState(
                vehicles = VehicleRepository.getVehicles()
            )
        )

    val uiState = _uiState.asStateFlow()

    fun onSearchChange(text: String) {

        _uiState.update {

            it.copy(
                searchText = text
            )
        }
    }

    fun addVehicle(vehicle: Vehicle) {

        VehicleRepository.addVehicle(vehicle)

        _uiState.update {

            it.copy(
                vehicles = VehicleRepository.getVehicles(),
                showAddVehicleDialog = false
            )
        }
    }

    fun showDialog() {

        _uiState.update {

            it.copy(
                showAddVehicleDialog = true
            )
        }
    }

    fun hideDialog() {

        _uiState.update {

            it.copy(
                showAddVehicleDialog = false
            )
        }
    }
}
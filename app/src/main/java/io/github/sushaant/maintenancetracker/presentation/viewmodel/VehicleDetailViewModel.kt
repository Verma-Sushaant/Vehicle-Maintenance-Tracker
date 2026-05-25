package io.github.sushaant.maintenancetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sushaant.maintenancetracker.data.repository.FuelRepository
import io.github.sushaant.maintenancetracker.data.repository.MaintenanceRepository
import io.github.sushaant.maintenancetracker.data.repository.ReminderRepository
import io.github.sushaant.maintenancetracker.data.repository.VehicleRepository
import io.github.sushaant.maintenancetracker.presentation.state.VehicleDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class VehicleDetailViewModel(
    private val vehicleId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(VehicleDetailState())

    val uiState = _uiState.asStateFlow()

    init {

        observeVehicle()
    }

    private fun observeVehicle() {

        viewModelScope.launch {

            VehicleRepository.vehicles.collectLatest {

                updateUiState()
            }
        }

        viewModelScope.launch {

            ReminderRepository.reminders.collectLatest {

                updateUiState()
            }
        }

        viewModelScope.launch {

            FuelRepository.fuelEntries.collectLatest {

                updateUiState()
            }
        }

        viewModelScope.launch {

            MaintenanceRepository.maintenanceEntries.collectLatest {

                updateUiState()
            }
        }
    }

    private fun updateUiState() {

        val vehicle =
            VehicleRepository.vehicles.value
                .firstOrNull {
                    it.id == vehicleId
                }

        _uiState.value =
            VehicleDetailState(

                vehicle = vehicle,

                fuelEntries =
                    FuelRepository
                        .getFuelEntries(vehicleId),

                reminders =
                    ReminderRepository
                        .getReminders(vehicleId)
            )
    }
}
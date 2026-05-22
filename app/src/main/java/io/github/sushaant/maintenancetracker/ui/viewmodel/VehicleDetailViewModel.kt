package io.github.sushaant.maintenancetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.sushaant.maintenancetracker.data.repository.FuelRepository
import io.github.sushaant.maintenancetracker.data.repository.ReminderRepository
import io.github.sushaant.maintenancetracker.data.repository.VehicleRepository
import io.github.sushaant.maintenancetracker.ui.state.VehicleDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VehicleDetailViewModel(
    vehicleId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(

            VehicleDetailState(

                vehicle =
                    VehicleRepository.getVehicleById(vehicleId),

                fuelEntries =
                    FuelRepository.getFuelEntries(vehicleId),

                reminders =
                    ReminderRepository.getReminders(vehicleId)
            )
        )

    val uiState = _uiState.asStateFlow()
}
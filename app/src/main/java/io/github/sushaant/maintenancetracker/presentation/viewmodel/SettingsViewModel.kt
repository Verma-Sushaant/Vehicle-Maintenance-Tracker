package io.github.sushaant.maintenancetracker.presentation.viewmodel

import android.app.Notification
import androidx.lifecycle.ViewModel
import io.github.sushaant.maintenancetracker.data.repository.FuelRepository
import io.github.sushaant.maintenancetracker.data.repository.MaintenanceRepository
import io.github.sushaant.maintenancetracker.data.repository.NotificationRepository
import io.github.sushaant.maintenancetracker.data.repository.ReminderRepository
import io.github.sushaant.maintenancetracker.data.repository.VehicleRepository
import io.github.sushaant.maintenancetracker.domain.model.NotificationItem
import io.github.sushaant.maintenancetracker.domain.model.NotificationType
import io.github.sushaant.maintenancetracker.presentation.state.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel() : ViewModel() {

    private val _uiState =
        MutableStateFlow(SettingsState())

    val uiState = _uiState.asStateFlow()

    val vehicles = VehicleRepository.vehicles

    fun toggleManageVehicles() {

        _uiState.update {

            it.copy(
                showManageVehicles =
                    !it.showManageVehicles
            )
        }
    }

    fun toggleServiceNotifications() {

        _uiState.update {

            it.copy(
                serviceNotifications =
                    !it.serviceNotifications
            )
        }
    }

    fun toggleFuelNotifications() {

        _uiState.update {

            it.copy(
                fuelNotifications =
                    !it.fuelNotifications
            )
        }
    }

    fun toggleReminderNotifications() {

        _uiState.update {

            it.copy(
                reminderNotifications =
                    !it.reminderNotifications
            )
        }
    }

    fun showDeleteDialog(
        vehicleId: Int
    ) {

        _uiState.update {

            it.copy(

                showDeleteDialog = true,

                selectedVehicleId = vehicleId
            )
        }
    }

    fun hideDeleteDialog() {

        _uiState.update {

            it.copy(

                showDeleteDialog = false,

                selectedVehicleId = null
            )
        }
    }

    fun deleteVehicle() {

        val vehicleId =
            _uiState.value.selectedVehicleId
                ?: return

        VehicleRepository.deleteVehicle(vehicleId)

        FuelRepository.deleteFuelEntries(vehicleId)

        MaintenanceRepository.deleteMaintenance(vehicleId)

        ReminderRepository.deleteReminders(vehicleId)

        NotificationRepository
            .deleteVehicleNotifications(vehicleId)

        NotificationRepository.addNotification(

            NotificationItem(

                id = System.currentTimeMillis().toInt(),

                title = "Vehicle Deleted",

                message =
                    "Vehicle removed successfully",

                time = "Now",

                vehicleId = vehicleId,

                type = NotificationType.WARNING
            )
        )

        hideDeleteDialog()
    }
}
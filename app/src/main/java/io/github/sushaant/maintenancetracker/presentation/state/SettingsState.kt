package io.github.sushaant.maintenancetracker.presentation.state

data class SettingsState(

    val serviceNotifications: Boolean = true,

    val fuelNotifications: Boolean = true,

    val reminderNotifications: Boolean = true,

    val showDeleteDialog: Boolean = false,

    val showManageVehicles: Boolean = false,

    val selectedVehicleId: Int? = null
)
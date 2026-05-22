package io.github.sushaant.maintenancetracker.ui.state

import io.github.sushaant.maintenancetracker.domain.model.Vehicle

data class HomeState(

    val vehicles: List<Vehicle> = emptyList(),

    val searchText: String = "",

    val showAddVehicleDialog: Boolean = false
)
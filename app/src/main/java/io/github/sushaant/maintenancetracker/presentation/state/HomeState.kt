package io.github.sushaant.maintenancetracker.presentation.state

import io.github.sushaant.maintenancetracker.domain.model.Vehicle

data class HomeState(

    val vehicles: List<Vehicle> = emptyList(),

    val filteredVehicles: List<Vehicle> = emptyList(),

    val searchText: String = "",

    val showAddVehicleDialog: Boolean = false
)
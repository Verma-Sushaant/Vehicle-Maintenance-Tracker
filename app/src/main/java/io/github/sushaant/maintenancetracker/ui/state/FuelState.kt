package io.github.sushaant.maintenancetracker.ui.state

import io.github.sushaant.maintenancetracker.domain.model.FuelEntry

data class FuelState(

    val fuelEntries: List<FuelEntry> = emptyList(),

    val showDialog: Boolean = false
)
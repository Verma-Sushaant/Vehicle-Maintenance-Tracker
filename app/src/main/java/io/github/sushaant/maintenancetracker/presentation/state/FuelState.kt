package io.github.sushaant.maintenancetracker.presentation.state

import io.github.sushaant.maintenancetracker.domain.model.FuelEntry

data class FuelState(

    val fuelEntries: List<FuelEntry> = emptyList(),

    val showDialog: Boolean = false
)
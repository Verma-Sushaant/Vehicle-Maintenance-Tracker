package io.github.sushaant.maintenancetracker.presentation.state

import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry

data class MaintenanceState(

    val maintenanceEntries: List<MaintenanceEntry> = emptyList(),

    val totalCost: Double = 0.0,

    val totalServices: Int = 0,

    val showDialog: Boolean = false
)
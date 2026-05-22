package io.github.sushaant.maintenancetracker.ui.state

import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry

data class MaintenanceState(

    val maintenanceEntries: List<MaintenanceEntry> = emptyList(),

    val showDialog: Boolean = false
)
package io.github.sushaant.maintenancetracker.presentation.state

import io.github.sushaant.maintenancetracker.domain.model.FuelEntry
import io.github.sushaant.maintenancetracker.domain.model.Reminder
import io.github.sushaant.maintenancetracker.domain.model.Vehicle

data class VehicleDetailState(

    val vehicle: Vehicle? = null,

    val fuelEntries: List<FuelEntry> = emptyList(),

    val reminders: List<Reminder> = emptyList()
)
package io.github.sushaant.maintenancetracker.domain.dummy_data

import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry

object MaintenanceData {

    val maintenanceEntries = listOf(

        MaintenanceEntry(
            id = 1,
            vehicleId = 1,
            maintenanceType = "Oil Change",
            cost = 8500.0,
            date = "05 Mar 2026",
            notes = "Engine oil replaced"
        ),

        MaintenanceEntry(
            id = 2,
            vehicleId = 1,
            maintenanceType = "Brake Inspection",
            cost = 4200.0,
            date = "11 Apr 2026",
            notes = "Front brake pads checked"
        ),

        MaintenanceEntry(
            id = 3,
            vehicleId = 2,
            maintenanceType = "Tyre Rotation",
            cost = 2500.0,
            date = "14 Feb 2026",
            notes = "Tyres balanced"
        ),

        MaintenanceEntry(
            id = 4,
            vehicleId = 3,
            maintenanceType = "Battery Check",
            cost = 1800.0,
            date = "17 Apr 2026",
            notes = "Battery health normal"
        ),

        MaintenanceEntry(
            id = 4,
            vehicleId = 3,
            maintenanceType = "Coolant Refill",
            cost = 1800.0,
            date = "02 May 2026",
            notes = "Coolant topped up"
        )
    )
}
package io.github.sushaant.maintenancetracker.domain.dummy_data

import io.github.sushaant.maintenancetracker.domain.model.FuelEntry

object FuelData {

    val fuelEntries = listOf(

        FuelEntry(
            id = 1,
            vehicleId = 1,
            odometer = 12450,
            litres = 42.5,
            totalCost = 5200.0,
            fuelType = "Premium Petrol",
            date = "12 Feb 2026"
        ),

        FuelEntry(
            id = 2,
            vehicleId = 1,
            odometer = 12890,
            litres = 38.0,
            totalCost = 4700.0,
            fuelType = "Premium Petrol",
            date = "20 Mar 2026"
        ),

        FuelEntry(
            id = 3,
            vehicleId = 2,
            odometer = 9800,
            litres = 50.0,
            totalCost = 6100.0,
            fuelType = "Petrol",
            date = "15 Mar 2026"
        ),

        FuelEntry(
            id = 4,
            vehicleId = 3,
            odometer = 5600,
            litres = 35.0,
            totalCost = 4200.0,
            fuelType = "Petrol",
            date = "18 Apr 2026"
        ),

        FuelEntry(
            id = 5,
            vehicleId = 4,
            odometer = 22400,
            litres = 30.0,
            totalCost = 3100.0,
            fuelType = "Petrol",
            date = "20 Apr 2026"
        )
    )
}
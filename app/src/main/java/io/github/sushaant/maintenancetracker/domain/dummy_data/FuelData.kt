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
            date = "12/02/2026"
        ),

        FuelEntry(
            id = 2,
            vehicleId = 1,
            odometer = 12890,
            litres = 38.0,
            totalCost = 4700.0,
            fuelType = "Premium Petrol",
            date = "20/03/2026"
        ),

        FuelEntry(
            id = 3,
            vehicleId = 2,
            odometer = 9800,
            litres = 50.0,
            totalCost = 6100.0,
            fuelType = "Petrol",
            date = "15/03/2026"
        ),

        FuelEntry(
            id = 4,
            vehicleId = 3,
            odometer = 5600,
            litres = 35.0,
            totalCost = 4200.0,
            fuelType = "Petrol",
            date = "18/04/2026"
        ),

        FuelEntry(
            id = 5,
            vehicleId = 3,
            odometer = 6120,
            litres = 40.0,
            totalCost = 4000.0,
            fuelType = "Petrol",
            date = "18/04/2026"
        ),

        FuelEntry(
            id = 6,
            vehicleId = 4,
            odometer = 22400,
            litres = 30.0,
            totalCost = 3100.0,
            fuelType = "Petrol",
            date = "20/04/2026"
        ),

        FuelEntry(
            id = 7,
            vehicleId = 2,
            odometer = 10350,
            litres = 42.0,
            totalCost = 5300.0,
            fuelType = "Petrol",
            date = "28/03/2026"
        ),

        FuelEntry(
            id = 8,
            vehicleId = 4,
            odometer = 22800,
            litres = 20.0,
            totalCost = 2000.0,
            fuelType = "Petrol",
            date = "18/04/2026"
        ),
    )
}
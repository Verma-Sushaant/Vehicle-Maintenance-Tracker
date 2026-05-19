package io.github.sushaant.maintenancetracker.domain.model

import io.github.sushaant.maintenancetracker.R

object VehicleData {

    val vehicles = listOf(

        Vehicle(
            id = 1,
            name = "BMW M4 Competition",
            modelYear = "2022",
            fuelType = "Petrol",
            mileage = "14 km/l",
            transmission = "Automatic",
            imageRes = R.drawable.bmw_m4
        ),

        Vehicle(
            id = 2,
            name = "Audi R8",
            modelYear = "2021",
            fuelType = "Petrol",
            mileage = "10 km/l",
            transmission = "Automatic",
            imageRes = R.drawable.audi_r8
        ),

        Vehicle(
            id = 3,
            name = "Toyota Supra",
            modelYear = "2024",
            fuelType = "Petrol",
            mileage = "9 km/l",
            transmission = "Manual",
            imageRes = R.drawable.toyota_gr_supra
        ),

        Vehicle(
            id = 4,
            name = "Honda Civic",
            modelYear = "2020",
            fuelType = "Petrol",
            mileage = "20 km/l",
            transmission = "Manual",
            imageRes = R.drawable.honda_civic
        )
    )
}
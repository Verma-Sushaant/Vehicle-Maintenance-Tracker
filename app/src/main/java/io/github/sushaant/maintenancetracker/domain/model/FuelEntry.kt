package io.github.sushaant.maintenancetracker.domain.model

data class FuelEntry(

    val id: Int,

    val vehicleId: Int,

    val odometer: Int,

    val litres: Double,

    val totalCost: Double,

    val fuelType: String,

    val date: String
)
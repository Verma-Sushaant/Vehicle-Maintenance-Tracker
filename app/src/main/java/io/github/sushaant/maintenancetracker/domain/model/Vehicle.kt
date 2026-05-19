package io.github.sushaant.maintenancetracker.domain.model

data class Vehicle(

    val id: Int,

    val name: String,

    val modelYear: String,

    val fuelType: String,

    val mileage: String,

    val transmission: String,

    val imageRes: Int
)
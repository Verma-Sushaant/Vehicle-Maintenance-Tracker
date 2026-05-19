package io.github.sushaant.maintenancetracker.domain.model

data class MaintenanceEntry(

    val id: Int,

    val vehicleId: Int,

    val maintenanceType: String,

    val cost: Double,

    val date: String,

    val notes: String
)
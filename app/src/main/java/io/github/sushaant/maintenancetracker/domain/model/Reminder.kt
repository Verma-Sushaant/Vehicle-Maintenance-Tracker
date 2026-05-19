package io.github.sushaant.maintenancetracker.domain.model

data class Reminder(

    val id: Int,

    val vehicleId: Int,

    val title: String,

    val dueInDays: Int,

    val priority: String,

    val status: String
)
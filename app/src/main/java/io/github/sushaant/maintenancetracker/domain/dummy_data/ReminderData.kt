package io.github.sushaant.maintenancetracker.domain.dummy_data

import io.github.sushaant.maintenancetracker.domain.model.Reminder

object ReminderData {

    val reminders = listOf(

        Reminder(
            id = 1,
            vehicleId = 1,
            title = "Oil Change",
            dueInDays = 3,
            priority = "Urgent",
            status = "Upcoming"
        ),

        Reminder(
            id = 2,
            vehicleId = 1,
            title = "Insurance Renewal",
            dueInDays = 12,
            priority = "Low",
            status = "Pending"
        ),

        Reminder(
            id = 3,
            vehicleId = 2,
            title = "Brake Inspection",
            dueInDays = 7,
            priority = "Urgent",
            status = "Pending"
        ),

        Reminder(
            id = 4,
            vehicleId = 3,
            title = "Tyre Pressure Check",
            dueInDays = 5,
            priority = "Average",
            status = "Upcoming"
        )
    )
}
package io.github.sushaant.maintenancetracker.ui.state

import io.github.sushaant.maintenancetracker.domain.model.Reminder

data class ReminderState(

    val reminders: List<Reminder> = emptyList(),

    val showDialog: Boolean = false
)
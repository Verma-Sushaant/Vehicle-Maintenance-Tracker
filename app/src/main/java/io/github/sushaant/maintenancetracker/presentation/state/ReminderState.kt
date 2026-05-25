package io.github.sushaant.maintenancetracker.presentation.state

import io.github.sushaant.maintenancetracker.domain.model.Reminder

data class ReminderState(

    val reminders: List<Reminder> = emptyList(),

    val urgentReminders: Int = 0,

    val totalReminders: Int = 0,

    val showDialog: Boolean = false,

    val showCompletionDialog: Boolean = false,

    val reminderToComplete: Reminder? = null,
)
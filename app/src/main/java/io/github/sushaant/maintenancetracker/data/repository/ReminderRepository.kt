package io.github.sushaant.maintenancetracker.data.repository

import androidx.compose.runtime.mutableStateListOf
import io.github.sushaant.maintenancetracker.domain.dummy_data.ReminderData
import io.github.sushaant.maintenancetracker.domain.model.Reminder

object ReminderRepository {

    private val reminders =
        mutableStateListOf<Reminder>().apply {

            addAll(ReminderData.reminders)
        }

    fun getReminders(vehicleId: Int): List<Reminder> {

        return reminders.filter {
            it.vehicleId == vehicleId
        }
    }

    fun addReminder(reminder: Reminder) {

        reminders.add(reminder)
    }
}
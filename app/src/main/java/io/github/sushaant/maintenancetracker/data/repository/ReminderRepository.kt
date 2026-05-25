package io.github.sushaant.maintenancetracker.data.repository

import io.github.sushaant.maintenancetracker.domain.dummy_data.ReminderData
import io.github.sushaant.maintenancetracker.domain.model.Reminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object ReminderRepository {

    private val _reminders =
        MutableStateFlow(ReminderData.reminders)

    val reminders =
        _reminders.asStateFlow()

    private val listeners = mutableListOf<() -> Unit>()

    private fun notifyChanges() {
        listeners.forEach { it() }
    }

    fun getReminders(vehicleId: Int): List<Reminder> {

        return _reminders.value.filter {
            it.vehicleId == vehicleId
        }
    }

    fun addReminder(reminder: Reminder) {

        _reminders.update { current ->
            current + reminder
        }

        notifyChanges()
    }

    fun removeReminder(reminderId: Int) {

        _reminders.update { current ->
            current.filterNot {
                it.id == reminderId
            }
        }

        notifyChanges()
    }

    fun deleteReminders(vehicleId: Int) {

        _reminders.update { reminders ->

            reminders.filterNot {
                it.vehicleId == vehicleId
            }
        }
    }
}
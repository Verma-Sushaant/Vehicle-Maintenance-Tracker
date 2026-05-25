package io.github.sushaant.maintenancetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sushaant.maintenancetracker.data.repository.MaintenanceRepository
import io.github.sushaant.maintenancetracker.data.repository.NotificationRepository
import io.github.sushaant.maintenancetracker.data.repository.ReminderRepository
import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry
import io.github.sushaant.maintenancetracker.domain.model.NotificationItem
import io.github.sushaant.maintenancetracker.domain.model.NotificationType
import io.github.sushaant.maintenancetracker.domain.model.Reminder
import io.github.sushaant.maintenancetracker.presentation.state.ReminderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReminderViewModel(
    private val vehicleId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(ReminderState())

    val uiState = _uiState.asStateFlow()

    init {

        observeReminders()
    }

    private fun observeReminders() {

        viewModelScope.launch {

            ReminderRepository.reminders.collectLatest { reminders ->

                val vehicleReminders =
                    reminders.filter {
                        it.vehicleId == vehicleId
                    }

                _uiState.update { it ->

                    it.copy(

                        reminders = vehicleReminders,

                        totalReminders = vehicleReminders.size,

                        urgentReminders =
                            vehicleReminders.count {
                                it.priority == "Urgent"
                            }
                    )
                }
            }
        }
    }

    fun showDialog() {

        _uiState.update {
            it.copy(showDialog = true)
        }
    }

    fun hideDialog() {

        _uiState.update {
            it.copy(showDialog = false)
        }
    }

    fun addReminder(reminder: Reminder) {

        ReminderRepository.addReminder(reminder)

        NotificationRepository.addNotification(

            NotificationItem(

                id = System.currentTimeMillis().toInt(),

                title = "New Reminder Added",

                message = "${reminder.title} scheduled",

                time = "Now",

                vehicleId = reminder.vehicleId,

                type = NotificationType.REMINDER
            )
        )

        hideDialog()
    }

    fun completeReminderTransaction(
        maintenanceType: String,
        maintenanceCost: Double,
        notes: String
    ) {

        val reminder =
            _uiState.value.reminderToComplete
                ?: return

        val maintenanceEntry = MaintenanceEntry(

            id = System.currentTimeMillis().toInt(),

            vehicleId = reminder.vehicleId,

            maintenanceType = maintenanceType,

            cost = maintenanceCost,

            date = "Today",

            notes = notes
        )

        MaintenanceRepository.addMaintenance(
            maintenanceEntry
        )

        ReminderRepository.removeReminder(
            reminder.id
        )

        NotificationRepository.addNotification(

            NotificationItem(

                id = System.currentTimeMillis().toInt(),

                title = "Service Completed",

                message =
                    "${reminder.title} added to maintenance history",

                time = "Now",

                vehicleId = reminder.vehicleId,

                type = NotificationType.MAINTENANCE
            )
        )

        dismissCompleteDialog()
    }

    fun selectReminder(
        reminder: Reminder
    ) {

        _uiState.update {

            it.copy(

                reminderToComplete = reminder,

                showCompletionDialog = true
            )
        }
    }

    fun dismissCompleteDialog() {

        _uiState.update {

            it.copy(

                reminderToComplete = null,

                showCompletionDialog = false
            )
        }
    }
}
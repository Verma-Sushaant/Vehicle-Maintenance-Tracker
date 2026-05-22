package io.github.sushaant.maintenancetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.sushaant.maintenancetracker.data.repository.ReminderRepository
import io.github.sushaant.maintenancetracker.domain.model.Reminder
import io.github.sushaant.maintenancetracker.ui.state.ReminderState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ReminderViewModel(
    vehicleId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(

            ReminderState(
                reminders =
                    ReminderRepository.getReminders(vehicleId)
            )
        )

    val uiState = _uiState.asStateFlow()

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

        _uiState.update {

            it.copy(

                reminders =
                    ReminderRepository
                        .getReminders(reminder.vehicleId),

                showDialog = false
            )
        }
    }
}
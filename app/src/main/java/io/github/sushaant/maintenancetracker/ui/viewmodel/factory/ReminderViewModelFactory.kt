package io.github.sushaant.maintenancetracker.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.sushaant.maintenancetracker.ui.viewmodel.ReminderViewModel

class ReminderViewModelFactory(
    private val vehicleId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return ReminderViewModel(vehicleId) as T
    }
}
package io.github.sushaant.maintenancetracker.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.sushaant.maintenancetracker.ui.viewmodel.NotificationViewModel

class NotificationViewModelFactory(
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return NotificationViewModel() as T
    }
}
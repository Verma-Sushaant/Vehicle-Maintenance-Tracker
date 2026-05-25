package io.github.sushaant.maintenancetracker.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.sushaant.maintenancetracker.presentation.viewmodel.SettingsViewModel

class SettingsViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return SettingsViewModel() as T
    }
}
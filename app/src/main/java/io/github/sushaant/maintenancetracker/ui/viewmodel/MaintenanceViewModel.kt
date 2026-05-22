package io.github.sushaant.maintenancetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.sushaant.maintenancetracker.data.repository.MaintenanceRepository
import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry
import io.github.sushaant.maintenancetracker.ui.state.MaintenanceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MaintenanceViewModel(
    vehicleId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(

            MaintenanceState(
                maintenanceEntries =
                    MaintenanceRepository
                        .getMaintenanceEntries(vehicleId)
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

    fun addMaintenance(entry: MaintenanceEntry) {

        MaintenanceRepository.addMaintenance(entry)

        _uiState.update {

            it.copy(

                maintenanceEntries =
                    MaintenanceRepository
                        .getMaintenanceEntries(entry.vehicleId),

                showDialog = false
            )
        }
    }
}
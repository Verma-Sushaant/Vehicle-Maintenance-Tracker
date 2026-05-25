package io.github.sushaant.maintenancetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sushaant.maintenancetracker.data.repository.MaintenanceRepository
import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry
import io.github.sushaant.maintenancetracker.presentation.state.MaintenanceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MaintenanceViewModel(
    private val vehicleId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(MaintenanceState())

    val uiState = _uiState.asStateFlow()

    init {

        observeMaintenance()
    }

    private fun observeMaintenance() {

        viewModelScope.launch {

            MaintenanceRepository.maintenanceEntries.collectLatest { entries ->

                val vehicleEntries =
                    entries.filter {
                        it.vehicleId == vehicleId
                    }

                _uiState.update {

                    it.copy(

                        maintenanceEntries = vehicleEntries,

                        totalCost = vehicleEntries.sumOf { entry ->
                            entry.cost
                        },

                        totalServices = vehicleEntries.size
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

    fun addMaintenance(entry: MaintenanceEntry) {

        MaintenanceRepository.addMaintenance(entry)

        hideDialog()
    }
}
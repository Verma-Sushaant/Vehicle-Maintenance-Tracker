package io.github.sushaant.maintenancetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.sushaant.maintenancetracker.data.repository.FuelRepository
import io.github.sushaant.maintenancetracker.domain.model.FuelEntry
import io.github.sushaant.maintenancetracker.ui.state.FuelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FuelViewModel(
    vehicleId: Int
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(

            FuelState(
                fuelEntries =
                    FuelRepository.getFuelEntries(vehicleId)
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

    fun addFuelEntry(entry: FuelEntry) {

        FuelRepository.addFuelEntry(entry)

        _uiState.update {

            it.copy(
                fuelEntries =
                    FuelRepository.getFuelEntries(entry.vehicleId),

                showDialog = false
            )
        }
    }
}
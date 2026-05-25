package io.github.sushaant.maintenancetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sushaant.maintenancetracker.data.repository.VehicleRepository
import io.github.sushaant.maintenancetracker.domain.model.Vehicle
import io.github.sushaant.maintenancetracker.presentation.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _searchText =
        MutableStateFlow("")

    private val _showDialog =
        MutableStateFlow(false)

    private val _uiState =
        MutableStateFlow(HomeState())

    val uiState = _uiState.asStateFlow()

    init {

        observeVehicles()
    }

    private fun observeVehicles() {

        viewModelScope.launch {

            VehicleRepository.vehicles.combine(
                _searchText
            ) { vehicles, searchText->

                val filteredVehicles =

                    if (searchText.isBlank()) {

                        vehicles

                    } else {

                        vehicles.filter {

                            it.name.contains(
                                searchText,
                                ignoreCase = true
                            )
                        }
                    }

                HomeState(

                    vehicles = vehicles,

                    filteredVehicles = filteredVehicles,

                    searchText = searchText,

                    showAddVehicleDialog =
                        _showDialog.value
                )

            }.collect { state ->

                _uiState.value = state
            }
        }
    }

    fun onSearchChange(text: String) {

        _searchText.value = text
    }

    fun showDialog() {

        _showDialog.value = true

        _uiState.update {
            it.copy(showAddVehicleDialog = true)
        }
    }

    fun hideDialog() {

        _showDialog.value = false

        _uiState.update {
            it.copy(showAddVehicleDialog = false)
        }
    }

    fun addVehicle(vehicle: Vehicle) {

        VehicleRepository.addVehicle(vehicle)

        hideDialog()
    }
}
package io.github.sushaant.maintenancetracker.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.sushaant.maintenancetracker.ui.viewmodel.VehicleDetailViewModel

class VehicleDetailViewModelFactory(
    private val vehicleId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return VehicleDetailViewModel(vehicleId) as T
    }
}
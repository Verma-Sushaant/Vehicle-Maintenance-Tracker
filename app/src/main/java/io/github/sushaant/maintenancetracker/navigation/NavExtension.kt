package io.github.sushaant.maintenancetracker.navigation

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.requireVehicleId(): Int {

    return arguments?.getInt("vehicleId")
        ?: error("Vehicle ID missing")
}
package io.github.sushaant.maintenancetracker.navigation

sealed class Routes(val route: String) {

    data object Home : Routes("home")

    data object VehicleDetails :
        Routes("vehicle_details/{vehicleId}") {

        fun createRoute(vehicleId: Int) =
            "vehicle_details/$vehicleId"
    }

    data object Fuel :
        Routes("fuel/{vehicleId}") {

        fun createRoute(vehicleId: Int) =
            "fuel/$vehicleId"
    }

    data object Maintenance :
        Routes("maintenance/{vehicleId}") {

        fun createRoute(vehicleId: Int) =
            "maintenance/$vehicleId"
    }

    data object Reminder :
        Routes("reminder/{vehicleId}") {

        fun createRoute(vehicleId: Int) =
            "reminder/$vehicleId"
    }

    data object Notifications :
        Routes("notifications")
}
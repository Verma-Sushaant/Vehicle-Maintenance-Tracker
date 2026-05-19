package io.github.sushaant.maintenancetracker.navigation

sealed class Screen(
    val route: String
) {

    data object Home : Screen("home")

    data object VehicleDetail : Screen(
        "vehicle_detail/{vehicleId}"
    ) {

        fun createRoute(
            vehicleId: Int
        ): String {

            return "vehicle_detail/$vehicleId"
        }
    }

    data object Fuel : Screen("fuel")

    data object Maintenance : Screen("maintenance")

    data object Analytics : Screen("analytics")

    data object Profile : Screen("profile")
}
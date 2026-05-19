package io.github.sushaant.maintenancetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.sushaant.maintenancetracker.ui.screens.details.VehicleDetailScreen
import io.github.sushaant.maintenancetracker.ui.screens.fuel.FuelLogScreen
import io.github.sushaant.maintenancetracker.ui.screens.home.HomeScreen
import io.github.sushaant.maintenancetracker.ui.screens.maintenance.MaintenanceScreen
import io.github.sushaant.maintenancetracker.ui.screens.notification.NotificationScreen
import io.github.sushaant.maintenancetracker.ui.screens.reminder.ReminderScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(

        navController = navController,

        startDestination = Routes.Home.route
    ) {

        // HOME

        composable(
            route = Routes.Home.route
        ) {

            HomeScreen(

                onVehicleClick = { vehicleId ->

                    navController.navigate(
                        Routes.VehicleDetails.createRoute(vehicleId)
                    )
                },

                onNotificationClick = {

                    navController.navigate(
                        Routes.Notifications.route
                    )
                }
            )
        }

        // VEHICLE DETAILS

        composable(

            route = Routes.VehicleDetails.route,

            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val vehicleId =
                backStackEntry.arguments?.getInt("vehicleId")
                    ?: 0

            VehicleDetailScreen(

                vehicleId = vehicleId,

                onBackClick = {
                    navController.popBackStack()
                },

                onFuelClick = {

                    navController.navigate(
                        Routes.Fuel.createRoute(vehicleId)
                    )
                },

                onMaintenanceClick = {

                    navController.navigate(
                        Routes.Maintenance.createRoute(vehicleId)
                    )
                },

                onReminderClick = {

                    navController.navigate(
                        Routes.Reminder.createRoute(vehicleId)
                    )
                }
            )
        }

        // FUEL

        composable(

            route = Routes.Fuel.route,

            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val vehicleId =
                backStackEntry.arguments?.getInt("vehicleId")
                    ?: 0

            FuelLogScreen(

                vehicleId = vehicleId,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // MAINTENANCE

        composable(

            route = Routes.Maintenance.route,

            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val vehicleId =
                backStackEntry.arguments?.getInt("vehicleId")
                    ?: 0

            MaintenanceScreen(

                vehicleId = vehicleId,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // REMINDER

        composable(

            route = Routes.Reminder.route,

            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val vehicleId =
                backStackEntry.arguments?.getInt("vehicleId")
                    ?: 0

            ReminderScreen(

                vehicleId = vehicleId,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // NOTIFICATIONS

        composable(
            route = Routes.Notifications.route
        ) {

            NotificationScreen(

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
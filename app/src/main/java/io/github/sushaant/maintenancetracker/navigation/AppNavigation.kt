package io.github.sushaant.maintenancetracker.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.sushaant.maintenancetracker.domain.model.NotificationType
import io.github.sushaant.maintenancetracker.presentation.screens.details.VehicleDetailScreen
import io.github.sushaant.maintenancetracker.presentation.screens.fuel.FuelLogScreen
import io.github.sushaant.maintenancetracker.presentation.screens.home.HomeScreen
import io.github.sushaant.maintenancetracker.presentation.screens.maintenance.MaintenanceScreen
import io.github.sushaant.maintenancetracker.presentation.screens.notification.NotificationScreen
import io.github.sushaant.maintenancetracker.presentation.screens.reminder.ReminderScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(

        navController = navController,

        startDestination = Routes.Home.route,

        enterTransition = {

            slideInHorizontally(

                initialOffsetX = { fullWidth ->
                    fullWidth
                },

                animationSpec = tween(220)

            ) + fadeIn(
                animationSpec = tween(220)
            )
        },

        exitTransition = {

            slideOutHorizontally(

                targetOffsetX = { fullWidth ->
                    -fullWidth / 8
                },

                animationSpec = tween(220)

            ) + fadeOut(
                animationSpec = tween(220)
            )
        },

        popEnterTransition = {

            slideInHorizontally(

                initialOffsetX = { fullWidth ->
                    -fullWidth / 4
                },

                animationSpec = tween(300)

            ) + fadeIn(
                animationSpec = tween(300)
            )
        },

        popExitTransition = {

            slideOutHorizontally(

                targetOffsetX = { fullWidth ->
                    fullWidth
                },

                animationSpec = tween(300)

            ) + fadeOut(
                animationSpec = tween(300)
            )
        }

    ) {

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
                    ?: return@composable

            FuelLogScreen(

                vehicleId = vehicleId,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

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
                    ?: return@composable

            MaintenanceScreen(

                vehicleId = vehicleId,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

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
                    ?: return@composable

            ReminderScreen(

                vehicleId = vehicleId,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.Notifications.route
        ) {

            NotificationScreen(

                onBackClick = {
                    navController.popBackStack()
                },

                onNotificationClick = { notification ->

                    when (notification.type) {

                        NotificationType.GENERAL -> {

                            notification.vehicleId?.let {

                                navController.navigate(
                                    Routes.VehicleDetails.createRoute(it)
                                )
                            }
                        }

                        NotificationType.FUEL -> {

                            notification.vehicleId?.let {

                                navController.navigate(
                                    Routes.Fuel.createRoute(it)
                                )
                            }
                        }

                        NotificationType.MAINTENANCE -> {

                            notification.vehicleId?.let {

                                navController.navigate(
                                    Routes.Maintenance.createRoute(it)
                                )
                            }
                        }

                        NotificationType.REMINDER -> {

                            notification.vehicleId?.let {

                                navController.navigate(
                                    Routes.Reminder.createRoute(it)
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
package io.github.sushaant.maintenancetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.sushaant.maintenancetracker.R
import io.github.sushaant.maintenancetracker.domain.model.Vehicle
import io.github.sushaant.maintenancetracker.ui.screens.details.VehicleDetailScreen
import io.github.sushaant.maintenancetracker.ui.screens.home.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {

    NavHost(

        navController = navController,

        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {

            HomeScreen(
                onVehicleClick = { vehicleId ->

                    navController.navigate(

                        Screen.VehicleDetail.createRoute(vehicleId)
                    )
                }
            )
        }

        composable(

            route = Screen.VehicleDetail.route,

            arguments = listOf(

                navArgument("vehicleId") {

                    type = NavType.IntType
                }
            )

        ) { backStackEntry ->

            val vehicleId = backStackEntry
                .arguments
                ?.getInt("vehicleId") ?: 0

            VehicleDetailScreen(

                vehicleId = vehicleId,

                onBackClick = {

                    navController.popBackStack()
                }
            )
        }
    }
}
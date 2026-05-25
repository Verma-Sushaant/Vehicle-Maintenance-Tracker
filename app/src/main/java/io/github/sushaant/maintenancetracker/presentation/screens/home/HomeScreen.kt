package io.github.sushaant.maintenancetracker.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.presentation.screens.home.components.AddVehicleDialog
import io.github.sushaant.maintenancetracker.presentation.screens.home.components.FloatingBottomBar
import io.github.sushaant.maintenancetracker.presentation.screens.home.components.GarageStatsCard
import io.github.sushaant.maintenancetracker.presentation.screens.home.components.GarageTopBar
import io.github.sushaant.maintenancetracker.presentation.screens.home.components.SearchBar
import io.github.sushaant.maintenancetracker.presentation.screens.home.components.VehicleCard
import io.github.sushaant.maintenancetracker.presentation.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.presentation.viewmodel.HomeViewModel
import io.github.sushaant.maintenancetracker.presentation.viewmodel.NotificationViewModel
import io.github.sushaant.maintenancetracker.presentation.viewmodel.factory.NotificationViewModelFactory

@Composable
fun HomeScreen(
    onVehicleClick: (Int) -> Unit,

    onNotificationClick: () -> Unit
) {

    val homeViewModel: HomeViewModel = viewModel()

    val notificationViewModel: NotificationViewModel =
        viewModel(
            factory = NotificationViewModelFactory()
        )

    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    val notificationUiState by notificationViewModel.uiState.collectAsStateWithLifecycle()

//    val filteredVehicles = homeUiState.filteredVehicles
//
    val dueSoonCount = 2

    val totalVehicles = homeUiState.vehicles.size

    val notificationCount = notificationUiState.unreadCount

    Box(
        modifier = Modifier.statusBarsPadding()
            .fillMaxSize()
            .background(BackgroundDark)
    ) {

        Scaffold(

            containerColor = BackgroundDark,

            bottomBar = {

                FloatingBottomBar(

                    onAddClick = {
                        homeViewModel.showDialog()
                    }
                )
            }

        ) { paddingValues ->

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                // FIXED TOP SECTION
                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 20.dp
                        ),

                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    GarageTopBar(
                        notificationCount,
                        onNotificationClick
                    )

                    SearchBar(

                        searchText = homeUiState.searchText,

                        onSearchTextChange = {
                            homeViewModel.onSearchChange(it)
                        }
                    )
                }

                // SCROLLABLE CONTENT
                LazyColumn(

                    modifier = Modifier.fillMaxSize(),

                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 180.dp
                    ),

                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    item {

                        GarageStatsCard(totalVehicles, dueSoonCount)
                    }

                    items(homeUiState.filteredVehicles) { vehicle ->

                        VehicleCard(
                            vehicle = vehicle,
                            onClick = { onVehicleClick(vehicle.id) }
                        )
                    }
                }
            }
        }

        if (homeUiState.showAddVehicleDialog) {

            AddVehicleDialog(

                onDismiss = {
                    homeViewModel.hideDialog()
                },

                onSave = { vehicle ->

                    homeViewModel.addVehicle(vehicle)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, apiLevel = 36)
@Composable
fun See() {
    HomeScreen({}, {})
}
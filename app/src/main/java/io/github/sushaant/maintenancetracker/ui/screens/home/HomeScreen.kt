package io.github.sushaant.maintenancetracker.ui.screens.home

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.ui.screens.home.components.AddVehicleDialog
import io.github.sushaant.maintenancetracker.ui.screens.home.components.FloatingBottomBar
import io.github.sushaant.maintenancetracker.ui.screens.home.components.GarageStatsCard
import io.github.sushaant.maintenancetracker.ui.screens.home.components.GarageTopBar
import io.github.sushaant.maintenancetracker.ui.screens.home.components.SearchBar
import io.github.sushaant.maintenancetracker.ui.screens.home.components.VehicleCard
import io.github.sushaant.maintenancetracker.ui.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onVehicleClick: (Int) -> Unit,

    onNotificationClick: () -> Unit
) {

    val viewModel: HomeViewModel = viewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val filteredVehicles =
        uiState.vehicles.filter {

            it.name.contains(
                uiState.searchText,
                ignoreCase = true
            )
        }

    val totalVehicles = uiState.vehicles.size

    val dueSoonCount = 2

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
                        viewModel.showDialog()
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

                    GarageTopBar(onNotificationClick)

                    SearchBar(

                        searchText = uiState.searchText,

                        onSearchTextChange = {
                            viewModel.onSearchChange(it)
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

                    items(filteredVehicles) { vehicle ->

                        VehicleCard(
                            vehicle = vehicle,
                            onClick = { onVehicleClick(vehicle.id) }
                        )
                    }
                }
            }
        }

        if (uiState.showAddVehicleDialog) {

            AddVehicleDialog(

                onDismiss = {
                    viewModel.hideDialog()
                }
            )
        }
    }
}
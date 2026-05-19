package io.github.sushaant.maintenancetracker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.R
import io.github.sushaant.maintenancetracker.domain.model.Vehicle
import io.github.sushaant.maintenancetracker.domain.model.VehicleData
import io.github.sushaant.maintenancetracker.ui.screens.home.components.*
import io.github.sushaant.maintenancetracker.ui.theme.BackgroundDark

@Composable
fun HomeScreen(onVehicleClick: (Int) -> Unit) {

    var showAddVehicleDialog by remember {
        mutableStateOf(false)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    val vehicles = VehicleData.vehicles

    val filteredVehicles = vehicles.filter {
        it.name.contains(
            searchText,
            ignoreCase = true
        )
    }

    val totalVehicles = vehicles.size

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
                        showAddVehicleDialog = true
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

                    GarageTopBar()

                    SearchBar(

                        searchText = searchText,

                        onSearchTextChange = {
                            searchText = it
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

        if (showAddVehicleDialog) {

            AddVehicleDialog(

                onDismiss = {
                    showAddVehicleDialog = false
                }
            )
        }
    }
}
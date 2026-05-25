package io.github.sushaant.maintenancetracker.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.presentation.screens.settings.components.SettingsSwitch
import io.github.sushaant.maintenancetracker.presentation.screens.settings.components.VehicleManagementCard
import io.github.sushaant.maintenancetracker.presentation.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent
import io.github.sushaant.maintenancetracker.presentation.theme.SurfaceDark
import io.github.sushaant.maintenancetracker.presentation.theme.TextPrimary
import io.github.sushaant.maintenancetracker.presentation.theme.TextSecondary
import io.github.sushaant.maintenancetracker.presentation.viewmodel.SettingsViewModel
import io.github.sushaant.maintenancetracker.presentation.viewmodel.factory.SettingsViewModelFactory

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {

    val viewModel: SettingsViewModel =
        viewModel(
            factory =
                SettingsViewModelFactory()
        )

    val uiState by
    viewModel.uiState.collectAsStateWithLifecycle()

    val vehicles by
    viewModel.vehicles.collectAsStateWithLifecycle()

    LazyColumn(

        modifier = modifier
            .fillMaxSize()
            .background(BackgroundDark),

        contentPadding = PaddingValues(20.dp),

        verticalArrangement =
            Arrangement.spacedBy(20.dp)
    ) {

        item {

            Text(
                text = "Settings",
                color = TextPrimary,
                style =
                    MaterialTheme.typography.headlineMedium
            )
        }

        item {

            SettingsSwitch(

                title = "Service Notifications",

                checked =
                    uiState.serviceNotifications,

                onCheckedChange = {
                    viewModel.toggleServiceNotifications()
                }
            )
        }

        item {

            SettingsSwitch(

                title = "Fuel Notifications",

                checked =
                    uiState.fuelNotifications,

                onCheckedChange = {
                    viewModel.toggleFuelNotifications()
                }
            )
        }

        item {

            SettingsSwitch(

                title = "Reminder Notifications",

                checked =
                    uiState.reminderNotifications,

                onCheckedChange = {
                    viewModel.toggleReminderNotifications()
                }
            )
        }

        item {

            Text(
                text = "Manage Vehicles",
                color = TextPrimary,
                style =
                    MaterialTheme.typography.titleMedium
            )
        }

        item {

            Button(

                onClick = {
                    viewModel.toggleManageVehicles()
                },

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            CyanAccent
                    )
            ) {

                Text(

                    text =
                        if (
                            uiState.showManageVehicles
                        )
                            "Hide Vehicles"
                        else
                            "Manage Vehicles",

                    color = Color.Black
                )
            }
        }

        if (uiState.showManageVehicles) {

            items(vehicles) { vehicle ->

                VehicleManagementCard(

                    vehicle = vehicle,

                    onDeleteClick = {

                        viewModel.showDeleteDialog(
                            vehicle.id
                        )
                    }
                )
            }
        }

        item {

            Text(
                text = "About",
                color = TextPrimary,
                style =
                    MaterialTheme.typography.titleMedium
            )
        }

        item {

            androidx.compose.foundation.layout.Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = SurfaceDark,
                        shape =
                            RoundedCornerShape(20.dp)
                    )
                    .padding(18.dp),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = "Version: 1.0.0",
                    color = TextSecondary
                )

                Text(
                    text =
                        "Developer: Sushaant Verma",
                    color = TextSecondary
                )

                Text(
                    text =
                        "Maintenance Tracker App",
                    color = TextSecondary
                )
            }
        }
    }

    if (uiState.showDeleteDialog) {

        AlertDialog(

            onDismissRequest = {
                viewModel.hideDeleteDialog()
            },

            confirmButton = {

                TextButton(

                    onClick = {

                        viewModel.deleteVehicle()
                    }
                ) {

                    Text(
                        text = "Delete",
                        color = Color.Red
                    )
                }
            },

            dismissButton = {

                TextButton(

                    onClick = {
                        viewModel.hideDeleteDialog()
                    }
                ) {

                    Text("Cancel")
                }
            },

            title = {

                Text(
                    text = "Delete Vehicle"
                )
            },

            text = {

                Text(
                    text =
                        "This action cannot be reversed."
                )
            }
        )
    }
}

@Preview
@Composable
fun see() {
    SettingsScreen()
}
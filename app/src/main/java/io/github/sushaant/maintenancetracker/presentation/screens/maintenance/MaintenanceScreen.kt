package io.github.sushaant.maintenancetracker.presentation.screens.maintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry
import io.github.sushaant.maintenancetracker.presentation.screens.maintenance.components.AddMaintenanceDialog
import io.github.sushaant.maintenancetracker.presentation.screens.maintenance.components.MaintenanceCard
import io.github.sushaant.maintenancetracker.presentation.screens.maintenance.components.MaintenanceOverviewCard
import io.github.sushaant.maintenancetracker.presentation.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent
import io.github.sushaant.maintenancetracker.presentation.theme.TextPrimary
import io.github.sushaant.maintenancetracker.presentation.viewmodel.MaintenanceViewModel
import io.github.sushaant.maintenancetracker.presentation.viewmodel.factory.MaintenanceViewModelFactory

@Composable
fun MaintenanceScreen(

    vehicleId: Int,

    onBackClick: () -> Unit = {}
) {
    val viewModel: MaintenanceViewModel =
        viewModel(
            factory = MaintenanceViewModelFactory(vehicleId)
        )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val maintenanceEntries = uiState.maintenanceEntries

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(20.dp),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onBackClick
                    ) {

                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Maintenance",
                        color = TextPrimary,
                        fontSize = 24.sp
                    )
                }
            }

            item {

                MaintenanceOverviewCard(

                    totalCost =
                        uiState.totalCost.toInt().toString(),

                    totalServices =
                        uiState.totalServices.toString()
                )
            }

            items(maintenanceEntries) { maintenance ->

                MaintenanceCard(
                    maintenance = maintenance
                )
            }
        }

        FloatingActionButton(

            onClick = {
                viewModel.showDialog()
            },

            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),

            containerColor = CyanAccent
        ) {

            Icon(
                imageVector = Icons.Outlined.Build,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
    if (uiState.showDialog) {

        AddMaintenanceDialog(

            onDismiss = {
                viewModel.hideDialog()
            },

            onSave = { type, cost, notes ->

                val newMaintenance = MaintenanceEntry(

                    id = System.currentTimeMillis().toInt(),

                    vehicleId = vehicleId,

                    maintenanceType = type,

                    cost = cost.toDoubleOrNull() ?: 0.0,

                    date = "Today",

                    notes = notes
                )

                viewModel.addMaintenance(newMaintenance)

                viewModel.hideDialog()
            }
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun GreetingPreview() {
//    MaintenanceTrackerTheme {
//        MaintenanceScreen(1)
//    }
//}
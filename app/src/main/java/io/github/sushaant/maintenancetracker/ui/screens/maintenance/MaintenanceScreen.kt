package io.github.sushaant.maintenancetracker.ui.screens.maintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.domain.dummy_data.MaintenanceData
import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry
import io.github.sushaant.maintenancetracker.ui.screens.fuel.FuelLogScreen
import io.github.sushaant.maintenancetracker.ui.screens.maintenance.components.AddMaintenanceDialog
import io.github.sushaant.maintenancetracker.ui.screens.maintenance.components.MaintenanceCard
import io.github.sushaant.maintenancetracker.ui.screens.maintenance.components.MaintenanceOverviewCard
import io.github.sushaant.maintenancetracker.ui.theme.*

@Composable
fun MaintenanceScreen(

    vehicleId: Int,

    onBackClick: () -> Unit = {}
) {

    var maintenanceEntries by remember {

        mutableStateOf(
            MaintenanceData
                .maintenanceEntries
                .filter {
                    it.vehicleId == vehicleId
                }
        )
    }

    val totalCost = maintenanceEntries.sumOf {
        it.cost
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

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
                            imageVector = Icons.Outlined.ArrowBack,
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
                    totalCost = totalCost.toInt().toString(),
                    totalServices = maintenanceEntries.size.toString()
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
                showDialog = true
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
    if (showDialog) {

        AddMaintenanceDialog(

            onDismiss = {
                showDialog = false
            },

            onSave = { type, cost, notes ->

                val newMaintenance = MaintenanceEntry(

                    id = maintenanceEntries.size + 1,

                    vehicleId = vehicleId,

                    maintenanceType = type,

                    cost = cost.toDoubleOrNull() ?: 0.0,

                    date = "Today",

                    notes = notes
                )

                maintenanceEntries =
                    maintenanceEntries + newMaintenance

                showDialog = false
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MaintenanceTrackerTheme {
        MaintenanceScreen(1)
    }
}
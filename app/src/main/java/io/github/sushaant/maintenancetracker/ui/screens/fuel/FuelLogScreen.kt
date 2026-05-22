package io.github.sushaant.maintenancetracker.ui.screens.fuel

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.domain.model.FuelEntry
import io.github.sushaant.maintenancetracker.domain.utils.calculateAverageFuelCostPerLitre
import io.github.sushaant.maintenancetracker.domain.utils.calculateMileage
import io.github.sushaant.maintenancetracker.domain.utils.calculateTotalFuelExpense
import io.github.sushaant.maintenancetracker.ui.screens.fuel.components.AddFuelDialog
import io.github.sushaant.maintenancetracker.ui.screens.fuel.components.FuelEntryCard
import io.github.sushaant.maintenancetracker.ui.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.ui.theme.BorderColor
import io.github.sushaant.maintenancetracker.ui.theme.CyanAccent
import io.github.sushaant.maintenancetracker.ui.theme.MaintenanceTrackerTheme
import io.github.sushaant.maintenancetracker.ui.theme.SurfaceLight
import io.github.sushaant.maintenancetracker.ui.theme.TextPrimary
import io.github.sushaant.maintenancetracker.ui.theme.TextSecondary
import io.github.sushaant.maintenancetracker.ui.viewmodel.FuelViewModel
import io.github.sushaant.maintenancetracker.ui.viewmodel.factory.FuelViewModelFactory

@SuppressLint("DefaultLocale")
@Composable
fun FuelLogScreen(

    vehicleId: Int,

    onBackClick: () -> Unit = {}
) {

    val viewModel: FuelViewModel =
        viewModel(
            factory = FuelViewModelFactory(vehicleId)
        )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val fuelEntries = uiState.fuelEntries

    val estimatedMileage = calculateMileage(fuelEntries)

    val totalFuelCost = calculateTotalFuelExpense(fuelEntries)

    val averageCostPerLitre = calculateAverageFuelCostPerLitre(fuelEntries)

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 120.dp
            ),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            // TOP BAR
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
                        text = "Fuel Logs",
                        color = TextPrimary,
                        fontSize = 24.sp
                    )
                }
            }

            // OVERVIEW
            item {

                FuelOverviewCard(

                    totalCost = totalFuelCost.toInt().toString(),

                    totalEntries = fuelEntries.size.toString(),

                    averageMileage = String.format(
                        "%.1f",
                        estimatedMileage
                    ),

                    averageFuelCost = String.format(
                        "%.0f",
                        averageCostPerLitre
                    )
                )
            }

            // RECENT ACTIVITY TITLE
            item {

                Text(
                    text = "Recent Fuel Entries",
                    color = TextPrimary,
                    fontSize = 18.sp
                )
            }

            // ENTRIES
            items(fuelEntries.reversed()) { entry ->

                FuelEntryCard(
                    fuelEntry = entry
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
                imageVector = Icons.Outlined.LocalGasStation,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
    if (uiState.showDialog) {

        AddFuelDialog(

            onDismiss = {
                viewModel.hideDialog()
            },

            onSave = { litres, cost, odometer, fuelType ->

                val newEntry = FuelEntry(

                    id = fuelEntries.size + 1,

                    vehicleId = vehicleId,

                    odometer = odometer.toIntOrNull() ?: 0,

                    litres = litres.toDoubleOrNull() ?: 0.0,

                    totalCost = cost.toDoubleOrNull() ?: 0.0,

                    fuelType = fuelType,

                    date = "Today"
                )

                viewModel.addFuelEntry(newEntry)

                viewModel.hideDialog()
            }
        )
    }
}

@Composable
fun FuelOverviewCard(

    totalCost: String,

    totalEntries: String,

    averageMileage: String,

    averageFuelCost: String
) {

    Column(

        modifier = Modifier
            .fillMaxWidth()

            .background(
                color = SurfaceLight,
                shape = RoundedCornerShape(28.dp)
            )

            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(28.dp)
            )

            .padding(22.dp),

        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Fuel Summary",
            color = TextPrimary,
            fontSize = 20.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            FuelOverviewItem(
                title = "Expense",
                value = "₹$totalCost"
            )

            FuelOverviewItem(
                title = "Entries",
                value = totalEntries
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            FuelOverviewItem(
                title = "Mileage",
                value = "$averageMileage km/l"
            )

            FuelOverviewItem(
                title = "Avg Cost/L",
                value = "₹$averageFuelCost"
            )
        }
    }
}

@Composable
fun FuelOverviewItem(
    title: String,
    value: String
) {

    Column {

        Text(
            text = title,
            color = TextSecondary,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = value,
            color = TextPrimary,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaintenanceTrackerTheme {
        FuelLogScreen(1)
    }
}
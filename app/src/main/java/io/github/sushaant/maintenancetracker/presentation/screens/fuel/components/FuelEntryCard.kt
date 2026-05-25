package io.github.sushaant.maintenancetracker.presentation.screens.fuel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.domain.model.FuelEntry
import io.github.sushaant.maintenancetracker.presentation.theme.BorderColor
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent
import io.github.sushaant.maintenancetracker.presentation.theme.SurfaceDark
import io.github.sushaant.maintenancetracker.presentation.theme.TextPrimary
import io.github.sushaant.maintenancetracker.presentation.theme.TextSecondary

@Composable
fun FuelEntryCard(
    fuelEntry: FuelEntry
) {

    Column(

        modifier = Modifier
            .fillMaxWidth()

            .background(
                color = SurfaceDark,
                shape = RoundedCornerShape(24.dp)
            )

            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(24.dp)
            )

            .padding(18.dp),

        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = fuelEntry.date,
                    color = TextSecondary,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "₹${fuelEntry.totalCost.toInt()}",
                    color = TextPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column {

                Text(
                    text = "${fuelEntry.fuelType} Fuel",
                    color = CyanAccent
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${fuelEntry.litres} L",
                    color = TextPrimary
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            FuelStat(
                title = "Odometer",
                value = "${fuelEntry.odometer} km"
            )

            FuelStat(
                title = "Cost/L",
                value = "₹${(fuelEntry.totalCost / fuelEntry.litres).toInt()}"
            )

            FuelStat(
                title = "Filled",
                value = "${fuelEntry.litres.toInt()} L"
            )
        }
    }
}

@Composable
fun FuelStat(
    title: String,
    value: String
) {

    Column {

        Text(
            text = title,
            color = TextSecondary,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            color = TextPrimary,
            fontWeight = FontWeight.SemiBold
        )
    }
}
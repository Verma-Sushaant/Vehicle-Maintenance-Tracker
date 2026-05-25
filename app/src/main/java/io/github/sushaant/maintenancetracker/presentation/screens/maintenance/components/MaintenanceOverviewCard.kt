package io.github.sushaant.maintenancetracker.presentation.screens.maintenance.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.presentation.theme.*

@Composable
fun MaintenanceOverviewCard(
    totalCost: String,
    totalServices: String
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()

            .background(
                color = SurfaceLight,
                shape = RoundedCornerShape(30.dp)
            )

            .padding(22.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {

            Text(
                text = "Total Maintenance",
                color = TextSecondary,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "₹$totalCost",
                color = TextPrimary,
                fontSize = 20.sp
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = "Services",
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = totalServices,
                color = CyanAccent,
                fontSize = 20.sp
            )
        }
    }
}
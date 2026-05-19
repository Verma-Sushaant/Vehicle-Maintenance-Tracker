package io.github.sushaant.maintenancetracker.ui.screens.maintenance.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.domain.model.MaintenanceEntry
import io.github.sushaant.maintenancetracker.ui.theme.*

@Composable
fun MaintenanceCard(
    maintenance: MaintenanceEntry
) {

    Column(

        modifier = Modifier
            .fillMaxWidth()

            .background(
                color = SurfaceDark,
                shape = RoundedCornerShape(26.dp)
            )

            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(26.dp)
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
                    text = maintenance.maintenanceType,
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = maintenance.date,
                    color = TextSecondary
                )
            }

            Text(
                text = "₹${maintenance.cost.toInt()}",
                color = CyanAccent,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column {

            Text(
                text = "Notes",
                color = TextSecondary,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = maintenance.notes,
                color = TextPrimary
            )
        }
    }
}
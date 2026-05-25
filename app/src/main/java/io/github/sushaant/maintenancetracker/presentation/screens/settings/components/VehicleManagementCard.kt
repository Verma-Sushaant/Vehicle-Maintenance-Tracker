package io.github.sushaant.maintenancetracker.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.domain.model.Vehicle
import io.github.sushaant.maintenancetracker.presentation.theme.*

@Composable
fun VehicleManagementCard(

    vehicle: Vehicle,

    onDeleteClick: () -> Unit
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SurfaceDark,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 18.dp,
                vertical = 16.dp
            ),

        horizontalArrangement =
            Arrangement.SpaceBetween,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text = vehicle.name,
            color = TextPrimary
        )

        IconButton(
            onClick = onDeleteClick
        ) {

            Icon(
                imageVector =
                    Icons.Outlined.Delete,

                contentDescription = null,

                tint = Color.Red
            )
        }
    }
}
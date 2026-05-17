// VehicleCard.kt

package io.github.sushaant.maintenancetracker.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.ui.theme.BorderColor
import io.github.sushaant.maintenancetracker.ui.theme.CyanGlow
import io.github.sushaant.maintenancetracker.ui.theme.PurpleGlow

@Composable
fun VehicleCard(
    vehicleName: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)

            .shadow(
                elevation = 22.dp,
                shape = RoundedCornerShape(30.dp),
                ambientColor = CyanGlow,
                spotColor = PurpleGlow
            )

            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(30.dp)
            )

            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xDD1B2335),
                        Color(0xCC111827)
                    )
                ),
                shape = RoundedCornerShape(30.dp)
            )

            .clickable { }

            .padding(22.dp),

        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {

            Text(
                text = vehicleName,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Petrol • Automatic",
                color = Color.LightGray
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            VehicleInfo(
                title = "Mileage",
                value = "14 km/l"
            )

            VehicleInfo(
                title = "Next Service",
                value = "12 Days"
            )
        }
    }
}

@Composable
fun VehicleInfo(
    title: String,
    value: String
) {

    Column {

        Text(
            text = title,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            color = Color(0xFF06B6D4),
            fontWeight = FontWeight.Bold
        )
    }
}
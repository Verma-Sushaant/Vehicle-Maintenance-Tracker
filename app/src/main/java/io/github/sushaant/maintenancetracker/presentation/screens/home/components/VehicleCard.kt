package io.github.sushaant.maintenancetracker.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.domain.model.Vehicle
import io.github.sushaant.maintenancetracker.presentation.theme.BorderColor
import io.github.sushaant.maintenancetracker.presentation.theme.CyanGlow
import io.github.sushaant.maintenancetracker.presentation.theme.PurpleGlow

@Composable
fun VehicleCard(
    vehicle: Vehicle,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)

            .shadow(
                elevation = 24.dp,
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

            .clickable {
                onClick()
            }
    ) {

        // IMAGE SECTION
        Box {

            Image(
                painter = painterResource(id = vehicle.imageRes),
                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    ),

                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)

                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xAA111827)
                            )
                        )
                    )
            )
        }

        Column(
            modifier = Modifier.padding(20.dp),

            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = vehicle.name,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${vehicle.modelYear} • ${vehicle.fuelType} • ${vehicle.transmission}",
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                VehicleInfo(
                    title = "Mileage",
                    value = vehicle.mileage
                )

                VehicleInfo(
                    title = "Service",
                    value = "12 Days"
                )
            }
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
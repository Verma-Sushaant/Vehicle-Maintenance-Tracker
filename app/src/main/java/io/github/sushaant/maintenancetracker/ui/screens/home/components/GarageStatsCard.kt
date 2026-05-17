package io.github.sushaant.maintenancetracker.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GarageStatsCard() {

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        Box(
            modifier = Modifier.weight(1f)
        ) {

            StatsItem(
                title = "Vehicles",
                value = "3"
            )
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {

            StatsItem(
                title = "Services",
                value = "2"
            )
        }
    }
}

@Composable
fun StatsItem(
    title: String,
    value: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF111827),
                        Color(0xFF1E293B)
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(18.dp),

        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = title,
            color = Color.LightGray
        )

        Text(
            text = value,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
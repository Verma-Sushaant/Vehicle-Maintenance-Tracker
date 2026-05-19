package io.github.sushaant.maintenancetracker.ui.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.ui.theme.*

@Composable
fun MiniAnalyticsCard(

    monthlyExpense: String,
    yearlyExpense: String,
    mileage: String,
    highestExpenseMonth: String
) {

    Column(

        modifier = Modifier
            .fillMaxWidth()

            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SurfaceLight,
                        SurfaceDark
                    )
                ),
                shape = RoundedCornerShape(30.dp)
            )

            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(30.dp)
            )

            .padding(22.dp),

        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Text(
            text = "Fuel Analytics",
            color = TextPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // EXPENSE ROW
        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AnalyticsStat(
                title = "Monthly",
                value = monthlyExpense
            )

            AnalyticsStat(
                title = "Yearly",
                value = yearlyExpense
            )

            AnalyticsStat(
                title = "Mileage",
                value = mileage
            )
        }

        // FAKE GRAPH
        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            AnalyticsProgressItem(
                label = "Fuel Efficiency",
                progress = 0.78f
            )

            AnalyticsProgressItem(
                label = "Service Health",
                progress = 0.92f
            )

            AnalyticsProgressItem(
                label = "Monthly Fuel Usage",
                progress = 0.64f
            )

            AnalyticsProgressItem(
                label = "Trip Performance",
                progress = 0.84f
            )
        }

        // INSIGHT
        Column {

            Text(
                text = "Insight",
                color = CyanAccent,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Highest fuel expense recorded in $highestExpenseMonth.",
                color = TextSecondary
            )
        }
    }
}

@Composable
fun AnalyticsStat(
    title: String,
    value: String
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            color = TextSecondary,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun GraphBar(
    progress: Float
) {

    LinearProgressIndicator(

        progress = { progress },

        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp),

        color = CyanAccent,

        trackColor = Color(0xFF1F2937)
    )
}

@Composable
fun AnalyticsProgressItem(
    label: String,
    progress: Float
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = label,
                color = TextSecondary,
                fontSize = 13.sp
            )

            Text(
                text = "${(progress * 100).toInt()}%",
                color = CyanAccent,
                fontSize = 12.sp
            )
        }

        GraphBar(progress = progress)
    }
}
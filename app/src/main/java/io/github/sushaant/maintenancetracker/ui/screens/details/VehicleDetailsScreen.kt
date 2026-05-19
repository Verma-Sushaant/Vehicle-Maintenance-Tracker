package io.github.sushaant.maintenancetracker.ui.screens.details

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.domain.dummy_data.FuelData
import io.github.sushaant.maintenancetracker.domain.dummy_data.MaintenanceData
import io.github.sushaant.maintenancetracker.domain.dummy_data.ReminderData
import io.github.sushaant.maintenancetracker.domain.dummy_data.VehicleData
import io.github.sushaant.maintenancetracker.ui.screens.details.components.MiniAnalyticsCard
import io.github.sushaant.maintenancetracker.ui.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.ui.theme.BorderColor
import io.github.sushaant.maintenancetracker.ui.theme.CyanAccent
import io.github.sushaant.maintenancetracker.ui.theme.CyanGlow
import io.github.sushaant.maintenancetracker.ui.theme.PurpleAccent
import io.github.sushaant.maintenancetracker.ui.theme.PurpleGlow
import io.github.sushaant.maintenancetracker.ui.theme.SurfaceDark
import io.github.sushaant.maintenancetracker.ui.theme.SurfaceLight
import io.github.sushaant.maintenancetracker.ui.theme.TextPrimary
import io.github.sushaant.maintenancetracker.ui.theme.TextSecondary

@Composable
fun VehicleDetailScreen(

    vehicleId: Int,

    onBackClick: () -> Unit = {},

    onFuelClick: () -> Unit = {},

    onMaintenanceClick: () -> Unit = {},

    onReminderClick: () -> Unit = {}
) {

    val vehicle = VehicleData.vehicles.firstOrNull {
        it.id == vehicleId
    }

    val fuelEntries = FuelData.fuelEntries.filter {
        it.vehicleId == vehicleId
    }

    val maintenanceEntries = MaintenanceData.maintenanceEntries.filter {
        it.vehicleId == vehicleId
    }

    val totalFuelExpense = fuelEntries.sumOf {
        it.totalCost
    }

    val totalLitres = fuelEntries.sumOf {
        it.litres
    }

    val avgMileage =
        if (fuelEntries.size >= 2) {

            val first = fuelEntries.first()
            val last = fuelEntries.last()

            (last.odometer - first.odometer) / totalLitres

        } else 0.0

    val reminders = ReminderData.reminders.filter {
        it.vehicleId == vehicleId
    }

    if (vehicle == null) {

        Box(
            modifier = Modifier.statusBarsPadding()
                .fillMaxSize()
                .background(BackgroundDark),

            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Vehicle Not Found",
                color = Color.White
            )
        }

        return
    }

    Box(
        modifier = Modifier.statusBarsPadding()
            .fillMaxSize()
            .background(BackgroundDark)
    ) {

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 40.dp
            ),

            verticalArrangement = Arrangement.spacedBy(20.dp)
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
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {

                        Text(
                            text = vehicle.name,
                            color = TextPrimary,
                            fontSize = 24.sp
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "${vehicle.modelYear} • ${vehicle.transmission}",
                            color = TextSecondary,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // IMAGE
            item {

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {

                    Image(
                        painter = painterResource(id = vehicle.imageRes),
                        contentDescription = null,

                        modifier = Modifier.fillMaxSize(),

                        contentScale = ContentScale.Crop
                    )

                    // DARK OVERLAY
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color(0xCC111827)
                                    )
                                )
                            )
                    )

                    // BORDER
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .border(
                                width = 1.dp,
                                color = BorderColor,
                                shape = RoundedCornerShape(20.dp)
                            )
                    )
                }
            }

            // INFO CHIPS
            item {

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Box(modifier = Modifier.weight(1f)) {

                        QuickInfoChip(
                            title = "Mileage",
                            value = vehicle.mileage
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {

                        QuickInfoChip(
                            title = "Fuel",
                            value = vehicle.fuelType
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {

                        QuickInfoChip(
                            title = "Service",
                            value = "3 Days"
                        )
                    }
                }
            }

            // ANALYTICS PREVIEW
            item {

                SectionTitle(
                    title = "Analytics"
                )
            }

            item {

                MiniAnalyticsCard(

                    monthlyExpense = "₹8,400",

                    yearlyExpense = "₹92,000",

                    mileage = "14 km/l",

                    highestExpenseMonth = "July"
                )
            }

            // MAINTENANCE
            val hasUrgentOrAvg = reminders.any { it.priority == "Urgent" || it.priority == "Average" }
            if(hasUrgentOrAvg) {
                item {

                    SectionTitle(
                        title = "Maintenance"
                    )
                }
            }

            items(
                reminders
                    .filter { it.priority == "Urgent" || it.priority == "Average"} // only urgent reminders
                    .take(2) // then take the first two
            ) { reminder ->

                ReminderCard(
                    title = reminder.title,
                    daysLeft = reminder.dueInDays,
                    priority = reminder.priority
                )
            }


            // QUICK ACTIONS
            item {

                SectionTitle(
                    title = "Quick Actions"
                )
            }

            item {

                Row(

                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    ActionButton(
                        icon = Icons.Default.LocalGasStation,
                        title = "Fuel Log",
                        onClick = onFuelClick
                    )

                    ActionButton(
                        icon = Icons.Default.Build,
                        title = "Service",
                        onClick = onMaintenanceClick
                    )

                    ActionButton(
                        icon = Icons.Default.Notifications,
                        title = "Reminder",
                        onClick = onReminderClick
                    )
                }
            }
        }
    }
}

@Composable
fun SectionTitle(
    title: String
) {

    Text(
        text = title,
        color = TextPrimary,
        fontSize = 20.sp
    )
}

@Composable
fun QuickInfoChip(
    title: String,
    value: String
) {

    Surface(

        modifier = Modifier.fillMaxWidth().shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(18.dp),
            ambientColor = CyanGlow,
            spotColor = PurpleGlow
        ),

        shape = RoundedCornerShape(18.dp),

        color = Color(0xFF1A2234)
    ) {

        Column(

            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 10.dp
            )
        ) {

            Text(
                text = title,
                color = TextSecondary,
                fontSize = 12.sp
            )

            Text(
                text = value,
                color = TextPrimary,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Composable
fun ReminderCard(
    title: String,
    daysLeft: Int,
    priority: String
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SurfaceDark,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(18.dp),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {

            Text(
                text = title,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text =
                    if (daysLeft == 1)
                        "1 day left"
                    else
                        "$daysLeft days left",

                color = when (priority) {

                    "Urgent" -> Color(0xFFFF5252)

                    "Average" -> Color(0xFFFFC107)

                    else -> Color(0xFF4CAF50)
                }
            )
        }

        Box(

            modifier = Modifier
                .size(14.dp)
                .background(
                    color = when (priority) {

                        "Urgent" -> Color(0xFFFF5252)

                        "Average" -> Color(0xFFFFC107)

                        else -> Color(0xFF4CAF50)
                    },

                    shape = CircleShape
                )
        )
    }
}

@Composable
fun ActionButton(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {

    ElevatedCard(

        modifier = Modifier.size(
            width = 100.dp,
            height = 100.dp
        ),

        onClick = onClick,

        colors = CardDefaults.elevatedCardColors(
            containerColor = SurfaceLight
        )

    ) {

        Column(

            modifier = Modifier.fillMaxSize(),

            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = CyanAccent
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                color = TextPrimary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun see() {
    VehicleDetailScreen(2,{})
}
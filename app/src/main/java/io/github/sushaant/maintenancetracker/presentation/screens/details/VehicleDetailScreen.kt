package io.github.sushaant.maintenancetracker.presentation.screens.details

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
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.domain.utils.calculateHighestExpenseEntry
import io.github.sushaant.maintenancetracker.domain.utils.calculateHighestExpenseMonth
import io.github.sushaant.maintenancetracker.domain.utils.calculateMileage
import io.github.sushaant.maintenancetracker.domain.utils.calculateTotalFuelExpense
import io.github.sushaant.maintenancetracker.presentation.screens.details.components.MiniAnalyticsCard
import io.github.sushaant.maintenancetracker.presentation.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.presentation.theme.BorderColor
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent
import io.github.sushaant.maintenancetracker.presentation.theme.CyanGlow
import io.github.sushaant.maintenancetracker.presentation.theme.PurpleGlow
import io.github.sushaant.maintenancetracker.presentation.theme.SurfaceDark
import io.github.sushaant.maintenancetracker.presentation.theme.SurfaceLight
import io.github.sushaant.maintenancetracker.presentation.theme.TextPrimary
import io.github.sushaant.maintenancetracker.presentation.theme.TextSecondary
import io.github.sushaant.maintenancetracker.presentation.viewmodel.VehicleDetailViewModel
import io.github.sushaant.maintenancetracker.presentation.viewmodel.factory.VehicleDetailViewModelFactory

@Composable
fun VehicleDetailScreen(

    vehicleId: Int,

    onBackClick: () -> Unit = {},

    onFuelClick: () -> Unit = {},

    onMaintenanceClick: () -> Unit = {},

    onReminderClick: () -> Unit = {}
) {

    val viewModel: VehicleDetailViewModel =
        viewModel(
            factory = VehicleDetailViewModelFactory(vehicleId)
        )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val vehicle = uiState.vehicle

    val fuelEntries = uiState.fuelEntries

    val reminders = uiState.reminders

    val averageMileage by remember(fuelEntries) {
        mutableDoubleStateOf(calculateMileage(fuelEntries))
    }

    val totalFuelExpense by remember(fuelEntries) {
        mutableDoubleStateOf(calculateTotalFuelExpense(fuelEntries))

    }

    val highestExpense =
        calculateHighestExpenseEntry(fuelEntries)

    val highestExpenseDate =
        highestExpense?.date ?: ""

    val highestExpenseMonth =
        calculateHighestExpenseMonth(fuelEntries)

    val fuelEfficiencyProgress =
        (averageMileage / 30.0)
            .coerceIn(0.0, 1.0)
            .toFloat()

    val urgentRemindersCount =
        reminders.count {
            it.priority == "Urgent"
        }

    val serviceHealthProgress =
        (1f - (urgentRemindersCount * 0.25f))
            .coerceIn(0f, 1f)

    val monthlyUsageProgress =
        (totalFuelExpense / 15000.0)
            .coerceIn(0.0, 1.0)
            .toFloat()

    val tripPerformanceProgress =
        (
                (
                        fuelEfficiencyProgress +
                                serviceHealthProgress
                        ) / 2f
                ).coerceIn(0f, 1f)

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
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
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
                    monthlyExpense = "₹${totalFuelExpense.toInt()}",

                    yearlyExpense = "₹${(totalFuelExpense * 12).toInt()}",

                    mileage = "${averageMileage.toInt()} km/l",

                    highestExpenseMonth = highestExpenseMonth,

                    highestExpenseDate = highestExpenseDate,

                    fuelEfficiencyProgress = fuelEfficiencyProgress,

                    serviceHealthProgress = serviceHealthProgress,

                    monthlyUsageProgress = monthlyUsageProgress,

                    tripPerformanceProgress = tripPerformanceProgress
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
                    .take(2), // then take the first two
                key = {it.id}
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

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    Box(modifier = Modifier.weight(1f)) {

                        ActionButton(
                            icon = Icons.Default.LocalGasStation,
                            title = "Fuel Log",
                            onClick = onFuelClick
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {

                        ActionButton(
                            icon = Icons.Default.Build,
                            title = "Service",
                            onClick = onMaintenanceClick
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {

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

@Preview(showBackground = true, showSystemUi = true, apiLevel = 36)
@Composable
fun See() {
    VehicleDetailScreen(1,{})
}
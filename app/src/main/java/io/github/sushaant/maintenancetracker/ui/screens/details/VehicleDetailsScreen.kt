package io.github.sushaant.maintenancetracker.ui.screens.details

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onBackClick: () -> Unit = {}
) {

    Box(
        modifier = Modifier
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

                    Text(
                        text = "Vehicle Details",
                        color = TextPrimary,
                        fontSize = 24.sp
                    )
                }
            }

            // HERO CARD
            item {

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)

                        .shadow(
                            elevation = 24.dp,
                            shape = RoundedCornerShape(32.dp),
                            ambientColor = CyanGlow,
                            spotColor = PurpleGlow
                        )

                        .border(
                            width = 1.dp,
                            color = BorderColor,
                            shape = RoundedCornerShape(32.dp)
                        )

                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF1B2335),
                                    Color(0xFF111827)
                                )
                            ),
                            shape = RoundedCornerShape(32.dp)
                        )

                        .padding(24.dp)
                ) {

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(
                                text = "BMW M4 Competition",
                                color = TextPrimary,
                                fontSize = 28.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "2022 Model",
                                color = TextSecondary
                            )
                        }

                        Row(

                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            QuickInfoChip(
                                title = "Mileage",
                                value = "14 km/l"
                            )

                            QuickInfoChip(
                                title = "Fuel",
                                value = "Petrol"
                            )
                        }
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

                PlaceholderCard(
                    title = "Fuel Consumption Graph",
                    subtitle = "Analytics integration coming next"
                )
            }

            // MAINTENANCE
            item {

                SectionTitle(
                    title = "Maintenance"
                )
            }

            item {

                ReminderCard(
                    title = "Oil Change",
                    daysLeft = "3 Days Left"
                )
            }

            item {

                ReminderCard(
                    title = "Brake Inspection",
                    daysLeft = "7 Days Left"
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
                        title = "Fuel Log"
                    )

                    ActionButton(
                        icon = Icons.Default.Build,
                        title = "Service"
                    )

                    ActionButton(
                        icon = Icons.Default.Notifications,
                        title = "Reminder"
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

        shape = RoundedCornerShape(18.dp),

        color = SurfaceLight
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
                color = TextPrimary
            )
        }
    }
}

@Composable
fun PlaceholderCard(
    title: String,
    subtitle: String
) {

    Box(

        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)

            .background(
                color = SurfaceDark,
                shape = RoundedCornerShape(28.dp)
            )

            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(28.dp)
            )

            .padding(22.dp)
    ) {

        Column {

            Text(
                text = title,
                color = TextPrimary,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = subtitle,
                color = TextSecondary
            )
        }
    }
}

@Composable
fun ReminderCard(
    title: String,
    daysLeft: String
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
                text = daysLeft,
                color = PurpleAccent
            )
        }

        Box(

            modifier = Modifier
                .size(14.dp)

                .background(
                    color = PurpleAccent,
                    shape = CircleShape
                )
        )
    }
}

@Composable
fun ActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String
) {

    ElevatedCard(

        modifier = Modifier.size(
            width = 100.dp,
            height = 100.dp
        ),

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
package io.github.sushaant.maintenancetracker.ui.screens.reminder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.domain.dummy_data.ReminderData
import io.github.sushaant.maintenancetracker.domain.model.Reminder
import io.github.sushaant.maintenancetracker.ui.screens.reminder.components.AddReminderDialog
import io.github.sushaant.maintenancetracker.ui.screens.reminder.components.ReminderCard
import io.github.sushaant.maintenancetracker.ui.theme.*

@Composable
fun ReminderScreen(

    vehicleId: Int,

    onBackClick: () -> Unit = {}
) {

    var reminders by remember {

        mutableStateOf(

            ReminderData.reminders.filter {
                it.vehicleId == vehicleId
            }
        )
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

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
                bottom = 120.dp
            ),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

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

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Reminders",
                        color = TextPrimary,
                        fontSize = 24.sp
                    )
                }
            }

            item {

                ReminderOverviewCard(
                    total = reminders.size.toString(),
                    urgent = reminders.count {
                        it.priority == "Urgent"
                    }.toString()
                )
            }

            items(reminders) { reminder ->

                ReminderCard(
                    reminder = reminder
                )
            }
        }

        FloatingActionButton(

            onClick = {
                showDialog = true
            },

            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),

            containerColor = CyanAccent
        ) {

            Icon(
                imageVector = Icons.Outlined.AddAlert,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }

    if (showDialog) {

        AddReminderDialog(

            onDismiss = {
                showDialog = false
            },

            onSave = { title, days, priority ->

                reminders = reminders + Reminder(

                    id = reminders.size + 1,

                    vehicleId = vehicleId,

                    title = title,

                    dueInDays = days.toIntOrNull() ?: 0,

                    priority = priority,

                    status = "Pending"
                )

                showDialog = false
            }
        )
    }
}

@Composable
fun ReminderOverviewCard(
    total: String,
    urgent: String
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SurfaceLight,
                shape = RoundedCornerShape(28.dp)
            )
            .padding(22.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {

            Text(
                text = "Total Reminders",
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = total,
                color = TextPrimary,
                fontSize = 24.sp
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = "Urgent",
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = urgent,
                color = PurpleAccent,
                fontSize = 24.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun see() {
    ReminderScreen(1)
}
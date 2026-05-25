package io.github.sushaant.maintenancetracker.presentation.screens.reminder

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.domain.model.Reminder
import io.github.sushaant.maintenancetracker.presentation.screens.maintenance.components.AddMaintenanceDialog
import io.github.sushaant.maintenancetracker.presentation.screens.reminder.components.AddReminderDialog
import io.github.sushaant.maintenancetracker.presentation.screens.reminder.components.ReminderCard
import io.github.sushaant.maintenancetracker.presentation.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent
import io.github.sushaant.maintenancetracker.presentation.theme.PurpleAccent
import io.github.sushaant.maintenancetracker.presentation.theme.SurfaceLight
import io.github.sushaant.maintenancetracker.presentation.theme.TextPrimary
import io.github.sushaant.maintenancetracker.presentation.theme.TextSecondary
import io.github.sushaant.maintenancetracker.presentation.viewmodel.ReminderViewModel
import io.github.sushaant.maintenancetracker.presentation.viewmodel.factory.ReminderViewModelFactory

@Composable
fun ReminderScreen(

    vehicleId: Int,

    onBackClick: () -> Unit = {}
) {
    val viewModel: ReminderViewModel =
        viewModel(
            factory = ReminderViewModelFactory(vehicleId)
        )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val reminders = uiState.reminders

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
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
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
                    total = uiState.totalReminders.toString(),
                    urgent = uiState.urgentReminders.toString()
                )
            }

            items(reminders) { reminder ->

                ReminderCard(

                    reminder = reminder,

                    onCompleteClick = {

                        viewModel.selectReminder(
                            reminder
                        )
                    }
                )
            }
        }

        FloatingActionButton(

            onClick = {
                viewModel.showDialog()
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

    if (uiState.showDialog) {

        AddReminderDialog(

            onDismiss = {
                viewModel.hideDialog()
            },

            onSave = { title, days, priority ->

                val newReminder = Reminder(

                    id = System.currentTimeMillis().toInt(),

                    vehicleId = vehicleId,

                    title = title,

                    dueInDays = days.toIntOrNull() ?: 0,

                    priority = priority,

                    status = "Pending"
                )

                viewModel.addReminder(newReminder)

                viewModel.hideDialog()
            }
        )
    }
    if (uiState.showCompletionDialog) {

        val reminder =
            uiState.reminderToComplete

        if (reminder != null) {

            AddMaintenanceDialog(

                onDismiss = {

                    viewModel.dismissCompleteDialog()
                },

                onSave = { type, cost, notes ->

                    viewModel.completeReminderTransaction(

                        maintenanceType = type,

                        maintenanceCost =
                            cost.toDoubleOrNull() ?: 0.0,

                        notes = notes
                    )
                }
            )
        }
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

@Preview(showBackground = true, showSystemUi = true, apiLevel = 36)
@Composable
fun See() {
    ReminderScreen(2)
}
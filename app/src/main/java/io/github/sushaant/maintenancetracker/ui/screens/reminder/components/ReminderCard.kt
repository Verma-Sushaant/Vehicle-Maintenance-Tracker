package io.github.sushaant.maintenancetracker.ui.screens.reminder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.domain.model.Reminder
import io.github.sushaant.maintenancetracker.ui.theme.*

@Composable
fun ReminderCard(
    reminder: Reminder
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
                text = reminder.title,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text =
                    if (reminder.dueInDays == 1)
                        "1 day left"
                    else
                        "${reminder.dueInDays} days left",
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = reminder.priority,
                color = when (reminder.priority) {

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
                    color = when (reminder.priority) {

                        "Urgent" -> Color(0xFFFF5252)

                        "Average" -> Color(0xFFFFC107)

                        else -> Color(0xFF4CAF50)
                    },

                    shape = CircleShape
                )
        )
    }
}
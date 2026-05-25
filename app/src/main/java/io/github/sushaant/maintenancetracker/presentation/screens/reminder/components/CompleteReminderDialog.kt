package io.github.sushaant.maintenancetracker.presentation.screens.reminder.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.sushaant.maintenancetracker.domain.model.Reminder

@Composable
fun CompleteReminderDialog(

    reminder: Reminder,

    onDismiss: () -> Unit,

    onComplete: (
        cost: String,
        notes: String
            ) -> Unit
) {

    var cost by remember {
        mutableStateOf("")
    }

    var notes by remember {
        mutableStateOf("")
    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text("Complete Service")
        },

        text = {

            androidx.compose.foundation.layout.Column {

                Text(
                    text = reminder.title
                )

                OutlinedTextField(
                    value = cost,
                    onValueChange = {
                        cost = it
                    },
                    label = {
                        Text("Cost")
                    }
                )

                OutlinedTextField(
                    value = notes,
                    onValueChange = {
                        notes = it
                    },
                    label = {
                        Text("Notes")
                    }
                )
            }
        },

        confirmButton = {

            Button(
                onClick = {
                    onComplete(cost, notes)
                }
            ) {
                Text("Complete")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}
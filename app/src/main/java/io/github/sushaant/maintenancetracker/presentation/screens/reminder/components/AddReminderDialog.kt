package io.github.sushaant.maintenancetracker.presentation.screens.reminder.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderDialog(

    onDismiss: () -> Unit,

    onSave: (
        title: String,
        days: String,
        priority: String
    ) -> Unit
) {

    var title by remember {
        mutableStateOf("")
    }

    var days by remember {
        mutableStateOf("")
    }

    var priority by remember {
        mutableStateOf("Average")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val priorityOptions = listOf(
        "Urgent",
        "Average",
        "Low"
    )

    AlertDialog(

        onDismissRequest = onDismiss,

        containerColor = SurfaceDark,

        shape = RoundedCornerShape(28.dp),

        title = {

            Text(
                text = "Add Reminder",
                color = TextPrimary
            )
        },

        text = {

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                OutlinedTextField(

                    value = title,

                    onValueChange = {
                        title = it
                    },

                    label = {
                        Text("Reminder Title")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    colors = customTextFieldColors()
                )

                OutlinedTextField(

                    value = days,

                    onValueChange = {
                        days = it
                    },

                    label = {
                        Text("Due In Days")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    colors = customTextFieldColors()
                )

                ExposedDropdownMenuBox(

                    expanded = expanded,

                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {

                    OutlinedTextField(

                        value = priority,

                        onValueChange = {},

                        readOnly = true,

                        label = {
                            Text("Priority")
                        },

                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },

                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),

                        colors = customTextFieldColors()
                    )

                    ExposedDropdownMenu(

                        expanded = expanded,

                        onDismissRequest = {
                            expanded = false
                        }
                    ) {

                        priorityOptions.forEach { option ->

                            DropdownMenuItem(

                                text = {
                                    Text(option)
                                },

                                onClick = {

                                    priority = option

                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },

        confirmButton = {

            Button(

                onClick = {

                    onSave(
                        title,
                        days,
                        priority
                    )
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = CyanAccent
                )
            ) {

                Text(
                    text = "Save",
                    color = Color.Black
                )
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text(
                    text = "Cancel",
                    color = TextSecondary
                )
            }
        }
    )
}

@Composable
fun customTextFieldColors() = OutlinedTextFieldDefaults.colors(

    focusedTextColor = Color.White,
    unfocusedTextColor = Color.White,

    focusedBorderColor = CyanAccent,
    unfocusedBorderColor = BorderColor,

    focusedContainerColor = Color(0xFF111827),
    unfocusedContainerColor = Color(0xFF111827),

    focusedLabelColor = CyanAccent,
    unfocusedLabelColor = TextSecondary,

    cursorColor = CyanAccent
)
package io.github.sushaant.maintenancetracker.presentation.screens.maintenance.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.domain.dummy_data.MaintenanceTypes
import io.github.sushaant.maintenancetracker.presentation.theme.BorderColor
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent
import io.github.sushaant.maintenancetracker.presentation.theme.SurfaceDark
import io.github.sushaant.maintenancetracker.presentation.theme.TextPrimary
import io.github.sushaant.maintenancetracker.presentation.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMaintenanceDialog(

    onDismiss: () -> Unit,

    onSave: (
        type: String,
        cost: String,
        notes: String
    ) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedType by remember {
        mutableStateOf("")
    }

    var cost by remember {
        mutableStateOf("")
    }

    var notes by remember {
        mutableStateOf("")
    }

    AlertDialog(

        onDismissRequest = onDismiss,

        containerColor = SurfaceDark,

        shape = RoundedCornerShape(30.dp),

        title = {

            Text(
                text = "Add Maintenance",
                color = TextPrimary
            )
        },

        text = {

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),

                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // DROPDOWN

                ExposedDropdownMenuBox(

                    expanded = expanded,

                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {

                    OutlinedTextField(

                        value = selectedType,

                        onValueChange = {},

                        readOnly = true,

                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),

                        label = {
                            Text("Maintenance Type")
                        },

                        colors = customTextFieldColors()
                    )

                    ExposedDropdownMenu(

                        expanded = expanded,

                        onDismissRequest = {
                            expanded = false
                        }
                    ) {

                        MaintenanceTypes.maintenanceTypes.forEach { type ->

                            DropdownMenuItem(

                                text = {
                                    Text(type)
                                },

                                onClick = {

                                    selectedType = type
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // COST

                OutlinedTextField(

                    value = cost,

                    onValueChange = {
                        cost = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Cost")
                    },

                    colors = customTextFieldColors()
                )

                // NOTES

                OutlinedTextField(

                    value = notes,

                    onValueChange = {
                        notes = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Notes")
                    },

                    minLines = 3,

                    colors = customTextFieldColors()
                )
            }
        },

        confirmButton = {

            Button(
                onClick = {

                    onSave(
                        selectedType,
                        cost,
                        notes
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
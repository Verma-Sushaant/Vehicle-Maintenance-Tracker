package io.github.sushaant.maintenancetracker.ui.screens.fuel.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFuelDialog(

    onDismiss: () -> Unit,

    onSave: (
        litres: String,
        cost: String,
        odometer: String,
        fuelType: String
    ) -> Unit
) {

    var litres by remember {
        mutableStateOf("")
    }

    var cost by remember {
        mutableStateOf("")
    }

    var odometer by remember {
        mutableStateOf("")
    }

    var fuelType by remember {
        mutableStateOf("Petrol")
    }

    val fuelOptions = listOf(
        "Petrol",
        "Diesel",
        "Premium Petrol",
        "Electric"
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    AlertDialog(

        onDismissRequest = onDismiss,

        containerColor = SurfaceDark,

        shape = RoundedCornerShape(28.dp),

        title = {

            Text(
                text = "Add Fuel Entry",
                color = TextPrimary
            )
        },

        text = {

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                OutlinedTextField(

                    value = litres,

                    onValueChange = {
                        litres = it
                    },

                    label = {
                        Text("Litres")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    singleLine = true,

                    colors = customTextFieldColors()
                )

                OutlinedTextField(

                    value = cost,

                    onValueChange = {
                        cost = it
                    },

                    label = {
                        Text("Total Cost")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    singleLine = true,

                    colors = customTextFieldColors()
                )

                OutlinedTextField(

                    value = odometer,

                    onValueChange = {
                        odometer = it
                    },

                    label = {
                        Text("Odometer Reading")
                    },

                    modifier = Modifier.fillMaxWidth(),

                    singleLine = true,

                    colors = customTextFieldColors()
                )

                // FUEL TYPE DROPDOWN
                ExposedDropdownMenuBox(

                    expanded = expanded,

                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {

                    OutlinedTextField(

                        value = fuelType,

                        onValueChange = {},

                        readOnly = true,

                        label = {
                            Text("Fuel Type")
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

                        fuelOptions.forEach { option ->

                            DropdownMenuItem(

                                text = {
                                    Text(option)
                                },

                                onClick = {

                                    fuelType = option

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
                        litres,
                        cost,
                        odometer,
                        fuelType
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
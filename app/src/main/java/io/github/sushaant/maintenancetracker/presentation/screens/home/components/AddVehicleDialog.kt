package io.github.sushaant.maintenancetracker.presentation.screens.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.domain.model.Vehicle
import io.github.sushaant.maintenancetracker.domain.utils.VehicleImageMapper
import io.github.sushaant.maintenancetracker.presentation.theme.*
import io.github.sushaant.maintenancetracker.presentation.viewmodel.VehicleDetailViewModel
import io.github.sushaant.maintenancetracker.presentation.viewmodel.factory.VehicleDetailViewModelFactory

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun AddVehicleDialog(
    onDismiss: () -> Unit,
    onSave: (Vehicle) -> Unit
) {

    val configuration = LocalConfiguration.current

    val dialogWidth = configuration.screenWidthDp.dp * 0.78f

    var selectedBrand by remember {
        mutableStateOf("")
    }

    var selectedModel by remember {
        mutableStateOf("")
    }

    var selectedYear by remember {
        mutableStateOf("")
    }

    // interaction states
    var modelClicked by remember {
        mutableStateOf(false)
    }

    var yearClicked by remember {
        mutableStateOf(false)
    }

    val vehicleModels = mapOf(

        "BMW" to listOf(
            "M4 Competition",
            "X5",
            "M8"
        ),

        "Audi" to listOf(
            "R8",
            "RS7",
            "A6"
        ),

        "Toyota" to listOf(
            "Supra",
            "Fortuner",
            "Camry"
        ),

        "Honda" to listOf(
            "Civic",
            "City",
            "Accord"
        )
    )

    val modelList = vehicleModels[selectedBrand] ?: emptyList()

    Dialog(
        onDismissRequest = onDismiss
    ) {

        Surface(

            modifier = Modifier.width(dialogWidth),

            shape = RoundedCornerShape(30.dp),

            color = SurfaceDark
        ) {

            Column(

                modifier = Modifier
                    .padding(22.dp)
                    .verticalScroll(rememberScrollState()),

                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                Text(
                    text = "Add Vehicle",
                    color = TextPrimary,
                    fontSize = 24.sp
                )

                // BRAND
                SearchableDropdown(

                    label = "Brand",

                    items = vehicleModels.keys.toList(),

                    selectedItem = selectedBrand,

                    onItemSelected = {

                        selectedBrand = it

                        selectedModel = ""
                        selectedYear = ""

                        modelClicked = false
                        yearClicked = false
                    }
                )

                // MODEL
                Column {

                    SearchableDropdown(

                        label = "Model",

                        items = modelList,

                        selectedItem = selectedModel,

                        enabled = selectedBrand.isNotEmpty(),

                        onExpandedAttempt = {

                            modelClicked = true
                        },

                        onItemSelected = {

                            selectedModel = it
                        }
                    )

                    if (
                        modelClicked &&
                        selectedBrand.isEmpty()
                    ) {

                        Text(
                            text = "Select brand first",
                            color = PurpleAccent,
                            fontSize = 12.sp
                        )
                    }
                }

                // YEAR
                Column {

                    OutlinedTextField(

                        value = selectedYear,

                        onValueChange = {

                            selectedYear = it.filter { char ->
                                char.isDigit()
                            }.take(4)
                        },

                        modifier = Modifier.fillMaxWidth(),

                        enabled = selectedModel.isNotEmpty(),

                        singleLine = true,

                        label = {
                            Text("Year")
                        },

                        shape = RoundedCornerShape(18.dp),

                        colors = customTextFieldColors()
                    )

                    if (
                        yearClicked &&
                        selectedModel.isEmpty()
                    ) {

                        Text(
                            text = "Select model first",
                            color = PurpleAccent,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(

                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    OutlinedButton(

                        onClick = onDismiss,

                        modifier = Modifier.weight(1f),

                        shape = RoundedCornerShape(18.dp),

                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = TextSecondary
                        )
                    ) {

                        Text("Cancel")
                    }

                    Button(

                        onClick = {

                            val vehicle = Vehicle(

                                id = System.currentTimeMillis().toInt(),

                                name = "$selectedBrand $selectedModel",

                                modelYear = selectedYear,

                                fuelType = "Petrol",

                                mileage = "0 km/l",

                                transmission = "Manual",

                                imageRes = VehicleImageMapper.getVehicleImage(
                                    selectedBrand,
                                    selectedModel
                                )
                            )

                            onSave(vehicle)

                            onDismiss()
                        },

                        modifier = Modifier
                            .weight(1f)
                            .height(58.dp),

                        shape = RoundedCornerShape(18.dp),

                        enabled =
                            selectedBrand.isNotEmpty() &&
                                    selectedModel.isNotEmpty() &&
                                    selectedYear.length == 4,

                        colors = ButtonDefaults.buttonColors(
                            containerColor = PurpleAccent
                        )
                    ) {

                        Text(
                            text = "Save",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
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
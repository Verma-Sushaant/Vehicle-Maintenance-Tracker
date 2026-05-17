package io.github.sushaant.maintenancetracker.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import io.github.sushaant.maintenancetracker.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableDropdown(

    label: String,

    items: List<String>,

    selectedItem: String,

    enabled: Boolean = true,

    onItemSelected: (String) -> Unit,

    onExpandedAttempt: () -> Unit = {}
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    var searchText by remember {
        mutableStateOf(selectedItem)
    }

    val filteredItems = items.filter {
        it.contains(searchText, ignoreCase = true)
    }

    ExposedDropdownMenuBox(

        expanded = expanded,

        onExpandedChange = {

            onExpandedAttempt()

            if (enabled) {

                expanded = !expanded
            }
        }

    ) {

        OutlinedTextField(

            value = searchText,

            onValueChange = {
                searchText = it
            },

            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryEditable)
                .fillMaxWidth(),

            enabled = enabled,

            label = {
                Text(label)
            },

            colors = OutlinedTextFieldDefaults.colors(

                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary,

                focusedBorderColor = CyanAccent,
                unfocusedBorderColor = BorderColor,

                focusedLabelColor = CyanAccent,
                unfocusedLabelColor = TextSecondary,

                focusedContainerColor = SurfaceLight,
                unfocusedContainerColor = SurfaceLight
            )
        )

        ExposedDropdownMenu(

            expanded = expanded,

            onDismissRequest = {
                expanded = false
            }

        ) {

            filteredItems.forEach { item ->

                DropdownMenuItem(

                    text = {
                        Text(item)
                    },

                    onClick = {

                        searchText = item

                        onItemSelected(item)

                        expanded = false
                    }
                )
            }
        }
    }
}
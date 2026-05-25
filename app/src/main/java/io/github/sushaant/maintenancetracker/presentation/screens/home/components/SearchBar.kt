package io.github.sushaant.maintenancetracker.presentation.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.presentation.theme.*

@Composable
fun SearchBar(

    searchText: String,

    onSearchTextChange: (String) -> Unit
) {

    OutlinedTextField(

        value = searchText,

        onValueChange = {
            onSearchTextChange(it)
        },

        modifier = Modifier.fillMaxWidth(),

        placeholder = {

            Text(
                text = "Search vehicle...",
                color = TextSecondary
            )
        },

        leadingIcon = {

            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = CyanAccent
            )
        },

        singleLine = true,

        shape = RoundedCornerShape(18.dp),

        colors = OutlinedTextFieldDefaults.colors(

            focusedContainerColor = SurfaceLight,
            unfocusedContainerColor = SurfaceLight,

            focusedBorderColor = CyanAccent,
            unfocusedBorderColor = BorderColor,

            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,

            cursorColor = CyanAccent
        )
    )
}
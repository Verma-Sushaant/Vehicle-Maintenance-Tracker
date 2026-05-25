package io.github.sushaant.maintenancetracker.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.presentation.theme.*

@Composable
fun SettingsSwitch(

    title: String,

    checked: Boolean,

    onCheckedChange: () -> Unit
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SurfaceDark,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 18.dp,
                vertical = 14.dp
            ),

        horizontalArrangement =
            Arrangement.SpaceBetween,

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(
            text = title,
            color = TextPrimary
        )

        Switch(

            checked = checked,

            onCheckedChange = {
                onCheckedChange()
            },

            modifier = Modifier.scale(0.82f),

            colors = SwitchDefaults.colors(

                checkedThumbColor = CyanAccent,

                checkedTrackColor =
                    CyanGlow.copy(alpha = 0.45f),

                checkedBorderColor = CyanAccent,

                uncheckedThumbColor =
                    TextSecondary,

                uncheckedTrackColor =
                    SurfaceLight,

                uncheckedBorderColor =
                    BorderColor
            )
        )
    }
}
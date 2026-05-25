package io.github.sushaant.maintenancetracker.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent

@Composable
fun GarageTopBar(
    notificationCount: Int,

    onNotificationClick: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween,

        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {

            Text(
                text = "Your Garage",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Manage all your vehicles",
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }

        Box {
            IconButton(onClick = onNotificationClick) {

                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            if (notificationCount > 0) {

                Box(

                    modifier = Modifier
                        .size(18.dp)
                        .background(
                            color = CyanAccent,
                            shape = CircleShape
                        )
                        .align(Alignment.TopEnd),

                    contentAlignment = Alignment.Center
                ) {

                    Text(

                        text = notificationCount.toString(),

                        color = Color.White,

                        fontSize = 10.sp,

                        fontStyle = FontStyle.Italic,

                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
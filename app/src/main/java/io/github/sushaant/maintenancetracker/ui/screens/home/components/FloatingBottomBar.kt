// FloatingBottomBar.kt

package io.github.sushaant.maintenancetracker.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.ui.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.ui.theme.PurpleAccent
import io.github.sushaant.maintenancetracker.ui.theme.PurpleGlow
import io.github.sushaant.maintenancetracker.ui.theme.SurfaceDark

@Composable
fun FloatingBottomBar(
    onAddClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            ),

        contentAlignment = Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)

                .shadow(
                    elevation = 30.dp,
                    shape = RoundedCornerShape(20.dp),
                    ambientColor = PurpleGlow,
                    spotColor = PurpleGlow
                )

                .background(
                    color = SurfaceDark,
                    shape = RoundedCornerShape(20.dp)
                )

                .border(
                    width = 1.dp,
                    color = Color(0x228B5CF6),
                    shape = RoundedCornerShape(20.dp)
                )

                .padding(horizontal = 28.dp),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(onClick = { }) {

                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            FloatingActionButton(
                onClick = onAddClick,

                modifier = Modifier
                    .size(50.dp),
//                    .offset(y = (-22).dp),

                containerColor = PurpleAccent,

//                shape = CircleShape
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            IconButton(onClick = { }) {

                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
@Preview
fun see() {
    FloatingBottomBar({})
}
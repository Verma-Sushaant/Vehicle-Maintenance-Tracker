package io.github.sushaant.maintenancetracker.ui.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sushaant.maintenancetracker.domain.dummy_data.NotificationData
import io.github.sushaant.maintenancetracker.domain.model.NotificationItem
import io.github.sushaant.maintenancetracker.ui.theme.*

@Composable
fun NotificationScreen(
    onBackClick: () -> Unit = {}
) {

    var notifications by remember {

        mutableStateOf(
            NotificationData.notifications
        )
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onBackClick
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Notifications",
                        color = TextPrimary,
                        fontSize = 24.sp
                    )
                }
            }

            items(notifications) { notification ->

                NotificationCard(
                    notification = notification
                )
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: NotificationItem
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SurfaceDark,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(18.dp),

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = notification.title,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = notification.message,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = notification.time,
                color = CyanAccent
            )
        }

        if (notification.isUnread) {

            Box(

                modifier = Modifier
                    .size(10.dp)
                    .background(
                        color = CyanAccent,
                        shape = CircleShape
                    )
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun see() {
//    NotificationScreen()
//}
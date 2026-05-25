package io.github.sushaant.maintenancetracker.presentation.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.github.sushaant.maintenancetracker.domain.model.NotificationItem
import io.github.sushaant.maintenancetracker.presentation.theme.BackgroundDark
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent
import io.github.sushaant.maintenancetracker.presentation.theme.CyanGlow
import io.github.sushaant.maintenancetracker.presentation.theme.SurfaceDark
import io.github.sushaant.maintenancetracker.presentation.theme.TextPrimary
import io.github.sushaant.maintenancetracker.presentation.theme.TextSecondary
import io.github.sushaant.maintenancetracker.presentation.viewmodel.NotificationViewModel
import io.github.sushaant.maintenancetracker.presentation.viewmodel.factory.NotificationViewModelFactory

@Composable
fun NotificationScreen(
    onBackClick: () -> Unit = {},
    onNotificationClick: (NotificationItem) -> Unit
) {

    val viewModel: NotificationViewModel =
        viewModel(
            factory = NotificationViewModelFactory()
        )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val notifications = uiState.notifications

    LaunchedEffect(Unit) {

        viewModel.markAllAsRead()
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
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
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

                    Spacer(modifier = Modifier.weight(1f))

                    Text(

                        text = "Clear",

                        color = CyanAccent,

                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {

                                viewModel.clearNotifications()
                            }
                            .border(
                                width = (1.5).dp,
                                color = CyanGlow,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(
                                top = 8.dp, bottom = 8.dp, start = 15.dp, end = 15.dp
                            )
                    )
                }
            }

            items(notifications) { notification ->

                NotificationCard(
                    notification = notification,
                    onClick = {
                        onNotificationClick(notification)
                    }
                )
            }
        }
    }
}

@Composable
fun NotificationCard(
    notification: NotificationItem,
    onClick: () -> Unit
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SurfaceDark,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {
                onClick()
            }
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

@Preview(showBackground = true, showSystemUi = true, apiLevel = 36)
@Composable
fun See() {
    NotificationScreen({},{})
}
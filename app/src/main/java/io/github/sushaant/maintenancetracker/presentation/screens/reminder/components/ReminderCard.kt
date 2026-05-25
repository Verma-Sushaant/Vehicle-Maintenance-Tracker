package io.github.sushaant.maintenancetracker.presentation.screens.reminder.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sushaant.maintenancetracker.domain.model.Reminder
import io.github.sushaant.maintenancetracker.presentation.theme.CyanAccent
import io.github.sushaant.maintenancetracker.presentation.theme.SurfaceDark
import io.github.sushaant.maintenancetracker.presentation.theme.TextPrimary
import io.github.sushaant.maintenancetracker.presentation.theme.TextSecondary

@Composable
fun ReminderCard(

    reminder: Reminder,

    onCompleteClick: () -> Unit
) {

    val dismissState =
        rememberSwipeToDismissBoxState(

            confirmValueChange = {

                if (
                    it ==
                    SwipeToDismissBoxValue.StartToEnd ||

                    it ==
                    SwipeToDismissBoxValue.EndToStart
                ) {

                    onCompleteClick()
                }

                false
            }
        )

    val swipeDirection =
        dismissState.dismissDirection

    SwipeToDismissBox(

        state = dismissState,

        backgroundContent = {

            val isSwiping =
                swipeDirection != null

            Box(

                modifier = Modifier

                    .fillMaxSize()

                    .background(

                        color =
                            if (isSwiping)
                                Color(0xFF1F2937)
                            else
                                Color.Transparent,

                        shape = RoundedCornerShape(24.dp)
                    )

                    .padding(horizontal = 24.dp),

                contentAlignment = when (swipeDirection) {

                    SwipeToDismissBoxValue.StartToEnd ->
                        Alignment.CenterStart

                    SwipeToDismissBoxValue.EndToStart ->
                        Alignment.CenterEnd

                    else ->
                        Alignment.Center
                }
            ) {

                if (isSwiping) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        if (
                            swipeDirection ==
                            SwipeToDismissBoxValue.StartToEnd
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Outlined.CheckCircle,

                                contentDescription = null,

                                tint = CyanAccent
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text(
                                text = "Mark Complete",
                                color = TextPrimary
                            )
                        }

                        if (
                            swipeDirection ==
                            SwipeToDismissBoxValue.EndToStart
                        ) {

                            Text(
                                text = "Mark Complete",
                                color = TextPrimary
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Icon(
                                imageVector =
                                    Icons.Outlined.CheckCircle,

                                contentDescription = null,

                                tint = CyanAccent
                            )
                        }
                    }
                }
            }
        }

    ) {

        Row(

            modifier = Modifier

                .fillMaxWidth()

                .background(

                    color = SurfaceDark,

                    shape = RoundedCornerShape(24.dp)
                )

                .padding(18.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = reminder.title,
                    color = TextPrimary
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(

                    text =
                        if (reminder.dueInDays == 1)
                            "1 day left"
                        else
                            "${reminder.dueInDays} days left",

                    color = TextSecondary
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(

                    text = reminder.priority,

                    color = when (
                        reminder.priority
                    ) {

                        "Urgent" ->
                            Color(0xFFFF5252)

                        "Average" ->
                            Color(0xFFFFC107)

                        else ->
                            Color(0xFF4CAF50)
                    }
                )
            }

            Box(

                modifier = Modifier

                    .size(14.dp)

                    .background(

                        color = when (
                            reminder.priority
                        ) {

                            "Urgent" ->
                                Color(0xFFFF5252)

                            "Average" ->
                                Color(0xFFFFC107)

                            else ->
                                Color(0xFF4CAF50)
                        },

                        shape = CircleShape
                    )
            )
        }
    }
}
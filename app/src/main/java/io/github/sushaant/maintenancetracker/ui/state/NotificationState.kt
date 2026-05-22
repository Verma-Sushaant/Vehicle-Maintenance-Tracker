package io.github.sushaant.maintenancetracker.ui.state

import io.github.sushaant.maintenancetracker.domain.model.NotificationItem

data class NotificationState(

    val notifications: List<NotificationItem> = emptyList()
)
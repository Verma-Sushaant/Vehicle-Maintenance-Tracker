package io.github.sushaant.maintenancetracker.presentation.state

import io.github.sushaant.maintenancetracker.domain.model.NotificationItem

data class NotificationState(

    val notifications: List<NotificationItem> = emptyList(),

    val unreadCount: Int = 0
)
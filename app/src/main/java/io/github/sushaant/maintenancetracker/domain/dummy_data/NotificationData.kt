package io.github.sushaant.maintenancetracker.domain.dummy_data

import io.github.sushaant.maintenancetracker.domain.model.NotificationItem

object NotificationData {

    val notifications = listOf(

        NotificationItem(
            id = 1,
            title = "Fuel Logged",
            message = "Fuel entry added for BMW M4",
            time = "2h ago",
            isUnread = true
        ),

        NotificationItem(
            id = 2,
            title = "Service Reminder",
            message = "Brake inspection due in 3 days",
            time = "5h ago",
            isUnread = true
        ),

        NotificationItem(
            id = 3,
            title = "Maintenance Updated",
            message = "Oil change marked completed",
            time = "Yesterday",
            isUnread = false
        )
    )
}
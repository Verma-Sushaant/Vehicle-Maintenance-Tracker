package io.github.sushaant.maintenancetracker.domain.model

data class NotificationItem(

    val id: Int,

    val title: String,

    val message: String,

    val time: String,

    val isUnread: Boolean = true,

    val vehicleId: Int? = null,

    val type: NotificationType
)

enum class NotificationType {

    REMINDER,
    MAINTENANCE,
    FUEL,
    GENERAL
}
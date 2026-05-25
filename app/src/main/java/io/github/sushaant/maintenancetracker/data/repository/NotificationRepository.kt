package io.github.sushaant.maintenancetracker.data.repository

import io.github.sushaant.maintenancetracker.domain.model.NotificationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object NotificationRepository {

    private val _notifications =
        MutableStateFlow<List<NotificationItem>>(emptyList())

    val notifications =
        _notifications.asStateFlow()

    fun getNotifications(): List<NotificationItem> {

        return _notifications.value.reversed()
    }

    fun addNotification(
        notification: NotificationItem
    ) {

        _notifications.update { current ->
            current + notification
        }
    }

    fun markAllAsRead() {

        _notifications.update { current ->

            current.map {
                it.copy(isUnread = false)
            }
        }
    }

    fun clearNotifications() {

        _notifications.value = emptyList()
    }

    fun unreadCount(): Int {

        return _notifications.value.count {
            it.isUnread
        }
    }

    fun deleteVehicleNotifications(vehicleId: Int) {

        _notifications.update { notifications ->

            notifications.filterNot {
                it.vehicleId == vehicleId
            }
        }
    }
}
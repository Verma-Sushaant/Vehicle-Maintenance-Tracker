package io.github.sushaant.maintenancetracker.data.repository

import androidx.compose.runtime.mutableStateListOf
import io.github.sushaant.maintenancetracker.domain.dummy_data.NotificationData
import io.github.sushaant.maintenancetracker.domain.model.NotificationItem

object NotificationRepository {

    private val notifications =
        mutableStateListOf<NotificationItem>().apply {

            addAll(NotificationData.notifications)
        }

    fun getNotifications(): List<NotificationItem> {

        return notifications
    }
}
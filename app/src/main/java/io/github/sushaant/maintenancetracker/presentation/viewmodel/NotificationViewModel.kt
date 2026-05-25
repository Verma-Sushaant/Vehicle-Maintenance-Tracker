package io.github.sushaant.maintenancetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sushaant.maintenancetracker.data.repository.NotificationRepository
import io.github.sushaant.maintenancetracker.presentation.state.NotificationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(NotificationState())

    val uiState = _uiState.asStateFlow()

    init {

        observeNotifications()
    }

    private fun observeNotifications() {

        viewModelScope.launch {

            NotificationRepository.notifications.collectLatest { notifications ->

                _uiState.update {

                    it.copy(

                        notifications = notifications.reversed(),

                        unreadCount =
                            notifications.count { notification ->
                                notification.isUnread
                            }
                    )
                }
            }
        }
    }

    fun markAllAsRead() {

        NotificationRepository.markAllAsRead()
    }

    fun clearNotifications() {

        NotificationRepository.clearNotifications()
    }
}
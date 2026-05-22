package io.github.sushaant.maintenancetracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import io.github.sushaant.maintenancetracker.data.repository.NotificationRepository
import io.github.sushaant.maintenancetracker.ui.state.NotificationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(

            NotificationState(
                notifications =
                    NotificationRepository
                        .getNotifications()
            )
        )

    val uiState = _uiState.asStateFlow()
}
package io.github.sushaant.maintenancetracker.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtils {

    fun currentTime(): String {

        val format = SimpleDateFormat(
            "hh:mm a",
            Locale.getDefault()
        )

        return format.format(Date())
    }
}
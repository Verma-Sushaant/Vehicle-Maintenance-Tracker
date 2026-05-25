package io.github.sushaant.maintenancetracker.domain.utils

import io.github.sushaant.maintenancetracker.R

object VehicleImageMapper {

    fun getVehicleImage(
        brand: String,
        model: String
    ): Int {

        val key =
            "${brand}_${model}"
                .lowercase()
                .replace(" ", "_")

        return when (key) {

            "bmw_m4_competition" -> R.drawable.bmw_m4

            "bmw_x5" -> R.drawable.bmw_x5

            "bmw_m8" -> R.drawable.bmw_m8

            "audi_r8" -> R.drawable.audi_r8

            "audi_rs7" -> R.drawable.audi_rs7

            "audi_a6" -> R.drawable.audi_a6

            "toyota_supra" -> R.drawable.toyota_gr_supra

            "toyota_fortuner" -> R.drawable.toyota_fortuner

            "toyota_camry" -> R.drawable.toyota_camry

            "honda_civic" -> R.drawable.honda_civic

            "honda_city" -> R.drawable.honda_city

            "honda_accord" -> R.drawable.honda_accord

            else -> R.drawable.default_car
        }
    }
}
package io.github.sushaant.maintenancetracker.domain.utils

import io.github.sushaant.maintenancetracker.domain.model.FuelEntry

fun calculateMileage(
    fuelEntries: List<FuelEntry>
): Double {

    val sortedEntries =
        fuelEntries.sortedBy {
            it.odometer
        }

    if (sortedEntries.size < 2)
        return 0.0

    var totalDistance = 0.0
    var totalFuelUsed = 0.0

    for (i in 1 until sortedEntries.size) {

        val previous = sortedEntries[i - 1]

        val current = sortedEntries[i]

        val distance =
            current.odometer - previous.odometer

        if (distance > 0) {

            totalDistance += distance

            totalFuelUsed += current.litres
        }
    }

    if (totalFuelUsed == 0.0)
        return 0.0

    return totalDistance / totalFuelUsed
}

fun calculateTotalFuelExpense(
    fuelEntries: List<FuelEntry>
): Double {

    return fuelEntries.sumOf {
        it.totalCost
    }
}

fun calculateAverageFuelCostPerLitre(
    fuelEntries: List<FuelEntry>
): Double {

    val totalFuel =
        fuelEntries.sumOf {
            it.litres
        }

    if (totalFuel == 0.0)
        return 0.0

    return fuelEntries.sumOf {
        it.totalCost
    } / totalFuel
}

fun calculateHighestExpenseEntry(
    fuelEntries: List<FuelEntry>
): FuelEntry? {

    return fuelEntries.maxByOrNull {
        it.totalCost
    }
}

fun calculateHighestExpenseMonth(
    fuelEntries: List<FuelEntry>
): String {

    val highestExpense =
        calculateHighestExpenseEntry(fuelEntries)
            ?: return "No fuel data"

    val date = highestExpense.date

    val monthNames = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    return when {

        date.contains("/") -> {

            val parts = date.split("/")

            val monthIndex =
                parts.getOrNull(1)
                    ?.toIntOrNull()
                    ?.minus(1)

            if (
                monthIndex != null &&
                monthIndex in monthNames.indices
            ) {

                monthNames[monthIndex]

            } else {

                "Unknown Month"
            }
        }

        date.contains("-") -> {

            val parts = date.split("-")

            val monthIndex =
                parts.getOrNull(1)
                    ?.toIntOrNull()
                    ?.minus(1)

            if (
                monthIndex != null &&
                monthIndex in monthNames.indices
            ) {

                monthNames[monthIndex]

            } else {

                "Unknown Month"
            }
        }

        else -> {
            date
        }
    }
}
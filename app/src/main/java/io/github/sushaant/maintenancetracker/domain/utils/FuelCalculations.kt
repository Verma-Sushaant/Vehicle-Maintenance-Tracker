package io.github.sushaant.maintenancetracker.domain.utils

fun calculateMileage(
    distanceTravelled: Double,
    fuelUsed: Double
): Double {

    if (fuelUsed == 0.0) return 0.0

    return distanceTravelled / fuelUsed
}

fun calculateFuelCostPerKm(
    totalCost: Double,
    distanceTravelled: Double
): Double {

    if (distanceTravelled == 0.0) return 0.0

    return totalCost / distanceTravelled
}
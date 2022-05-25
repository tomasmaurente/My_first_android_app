package com.example.domain.entities

data class ParkingLot(
    val spot: Integer,
    val day: String,
    val month_and_year: String,
    val hour: String,
    val reservations: List<LotReservation>,
)

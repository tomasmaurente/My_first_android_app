package com.example.data.entities

data class ParkingLotRoom(
    val spot: Integer,
    val day: String,
    val month_and_year: String,
    val hour: String,
    val reservations: List<LotReservation>,
)

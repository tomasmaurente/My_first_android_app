package com.example.domain.entities

import com.example.data.entities.LotReservation

class ParkingLot(
    val spot: Int = 0,
    val day: String = "",
    val month_and_year: String = "",
    val hour: String = "",
    val reservations: List<LotReservation> = listOf(),
)

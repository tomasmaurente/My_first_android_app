package com.example.domain.entities

class Lot(
    val spot: Int = 0,
    val reservations: List<Reservation> = listOf(),
)

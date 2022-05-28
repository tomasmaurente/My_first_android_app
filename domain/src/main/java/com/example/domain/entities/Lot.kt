package com.example.domain.entities

import java.io.Serializable

class Lot(
    val spot: Int = 0,
    val reservations: List<Reservation> = listOf(),
) : Serializable

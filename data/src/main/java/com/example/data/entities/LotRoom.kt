package com.example.data.entities

import com.example.domain.entities.Reservation

data class LotRoom(
            val spot: Int,
            val reservations: List<Reservation>,
)

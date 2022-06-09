package com.example.data.local_data_base.entities

import com.example.domain.entities.Reservation

data class LotRoom(
            val spot: Int,
            val reservations: List<Reservation>,
)

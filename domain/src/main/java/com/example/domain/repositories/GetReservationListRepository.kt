package com.example.domain.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Lot

interface GetReservationListRepository {
    fun getReservationList(lot: Lot): List<Reservation>
}
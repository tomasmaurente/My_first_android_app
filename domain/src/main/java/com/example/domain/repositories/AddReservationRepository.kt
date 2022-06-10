package com.example.domain.repositories

import com.example.domain.entities.Lot

interface AddReservationRepository {
    fun addReservation(lot: Int, startDateTime: Long, endDateTime: Long, authorizationCode: String): Boolean
}
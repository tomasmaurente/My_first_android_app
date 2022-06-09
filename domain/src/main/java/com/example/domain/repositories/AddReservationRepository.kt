package com.example.domain.repositories

import com.example.domain.entities.Lot

interface AddReservationRepository {
    fun addReservation(lot: Int, startDateTime: String, endDateTime: String, authorizationCode: Int): Boolean
}
package com.example.domain.repositories

import com.example.domain.entities.Reservation

interface DeleteReservationRepository {
    fun deleteReservation(reservation: Reservation, authorizationCode: String): Boolean
}
package com.example.domain.repositories

import com.example.domain.entities.LotReservation

interface DeleteReservationRepository {
    fun deleteReservation(reservation: LotReservation, authorizationCode: Int): Boolean
}
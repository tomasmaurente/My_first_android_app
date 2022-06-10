package com.example.domain.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result

interface DeleteReservationRepository {
    suspend fun deleteReservation(parkingId: String, reservation: Reservation, authorizationCode: String): Result<Boolean>
}
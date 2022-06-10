package com.example.domain.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result

interface AddReservationRepository {
    suspend fun addReservation(parkingId: String, reservation: Reservation): Result<Boolean>
}
package com.example.domain.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result

// FIXME: This belongs to the ReservationRepository
interface AddRepository {
    suspend fun addReservation(reservation: Reservation): Result<Boolean>
}
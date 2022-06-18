package com.example.domain.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result

interface DeleteRepository {
    suspend fun deleteReservation(reservation: Reservation): Result<Boolean>
}
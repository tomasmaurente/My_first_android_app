package com.example.domain.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result

interface AddRepository {
    suspend fun addReservation(parkingId: String,
                               reservation: Reservation,
                               localDataBase: Boolean): Result<Boolean>
    suspend fun addLot(lot: Int): Result<Boolean>
    suspend fun addReservation(reservation: Reservation): Result<Boolean>
}
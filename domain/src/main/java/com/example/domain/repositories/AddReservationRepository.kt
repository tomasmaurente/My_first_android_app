package com.example.domain.repositories

import com.example.domain.entities.ParkingLot
import java.time.LocalDateTime

interface AddReservationRepository {
    fun addReservation(lot: ParkingLot, startDateTime: String, endDateTime: String, authorizationCode: Int): Boolean
}
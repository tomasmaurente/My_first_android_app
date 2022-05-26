package com.example.data.repositories

import com.example.domain.entities.ParkingLot
import com.example.domain.repositories.AddReservationRepository

class AddReservationRepositoryImp: AddReservationRepository {
    override fun addReservation(
        lot: ParkingLot,
        startDateTime: String,
        endDateTime: String,
        authorizationCode: Int
    ): Boolean {
        return true
    }
}
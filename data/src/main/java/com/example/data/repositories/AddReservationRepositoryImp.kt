package com.example.data.repositories

import com.example.domain.entities.Lot
import com.example.domain.repositories.AddReservationRepository

class AddReservationRepositoryImp: AddReservationRepository {
    override fun addReservation(
        lot: Int,
        startDateTime: String,
        endDateTime: String,
        authorizationCode: Int
    ): Boolean {
        return true
    }
}
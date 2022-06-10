package com.example.data.repositories

import com.example.data.service.ParkingService
import com.example.domain.repositories.AddReservationRepository

class AddReservationRepositoryImp: AddReservationRepository {

    private val addService : ParkingService = ParkingService()

    override fun addReservation(
        lot: Int,
        startDateTime: Long,
        endDateTime: Long,
        authorizationCode: String
    ): Boolean {
        val result = addService
        return true
    }
}
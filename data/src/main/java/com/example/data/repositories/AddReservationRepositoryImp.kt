package com.example.data.repositories

import com.example.data.responseObjects.ReservationRequest
import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.AddReservationRepository

class AddReservationRepositoryImp: AddReservationRepository {

    private val addService: ParkingService = ParkingService()

    override suspend fun addReservation(
        parkingId: String,
        reservation: Reservation
    ): Result<Boolean> {
        val newReservation = ReservationRequest(
            reservation.authorizationCode,
            reservation.startDateTimeInMillis.toString(),
            reservation.endDateTimeInMillis.toString(),
            reservation.parkingLot
        )
        var result = addService.addReservation(parkingId, newReservation)
        return when (result) {
            is Result.Success -> {
                Result.Success(result.value as Boolean)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }
}

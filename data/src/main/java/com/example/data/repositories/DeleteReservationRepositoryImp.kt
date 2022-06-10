package com.example.data.repositories

import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteReservationRepository

class DeleteReservationRepositoryImp: DeleteReservationRepository {

    private val deleteService: ParkingService = ParkingService()

    override suspend fun deleteReservation(parkingId: String, reservation: Reservation, authorizationCode: String
    ): Result<Boolean> {
        var result = deleteService.deleteReservation(parkingId, reservation.id)
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


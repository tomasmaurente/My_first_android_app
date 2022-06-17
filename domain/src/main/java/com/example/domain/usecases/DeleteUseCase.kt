package com.example.domain.usecases

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteRepository
import java.lang.RuntimeException

class DeleteReservationUseCase{
    lateinit var deleteReservationRepository: DeleteRepository
    suspend operator fun invoke(parkingId: String,
                                reservation: Reservation,
                                authorizationCode: String
                                                    ): Result<Boolean> {
        return if (authorizationCode == reservation.authorizationCode) {
            deleteReservationRepository.deleteReservation(parkingId, reservation)
        } else {
            Result.Failure(RuntimeException())
        }
    }
}
package com.example.domain.usecases

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteRepository
import java.lang.RuntimeException

class DeleteUseCase(var deleteReservationRepository: DeleteRepository){

    suspend operator fun invoke(reservation: Reservation,
                                authorizationCode: String
                                                    ): Result<Boolean> {
        return if (authorizationCode == reservation.authorizationCode) {
            deleteReservationRepository.deleteReservation(reservation)
        } else {
            Result.Failure(RuntimeException())
        }
    }
}
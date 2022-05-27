package com.example.domain.usecases

import com.example.domain.entities.Reservation
import com.example.domain.repositories.DeleteReservationRepository

class DeleteResevationUseCase{
        lateinit var deleteReservationRepository: DeleteReservationRepository
        operator fun invoke(reservation: Reservation, authorizationCode: String ) = deleteReservationRepository.deleteReservation(
                reservation,authorizationCode)
}
package com.example.domain.usecases

import com.example.domain.entities.LotReservation
import com.example.domain.repositories.AddReservationRepository
import com.example.domain.repositories.DeleteReservationRepository

class DeleteResevationUseCase{
        lateinit var deleteReservationRepository: DeleteReservationRepository
        operator fun invoke(id: Int, getFromRemote: Boolean) = deleteReservationRepository.deleteReservation(reservation,authorizationCode)
}
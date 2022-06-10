package com.example.domain.usecases

import com.example.domain.repositories.AddReservationRepository

class AddReservationUseCase {
    lateinit var addReservationRepository: AddReservationRepository
    operator fun invoke(lot: Int,
                        startDateTime: Long,
                        endDateTime: Long,
                        authorizationCode: String ): Result<AddReservationResponse> {
        return addReservationRepository.addReservation(lot, startDateTime, endDateTime, authorizationCode)
    }
}
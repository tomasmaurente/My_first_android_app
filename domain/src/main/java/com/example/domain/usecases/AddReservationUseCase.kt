package com.example.domain.usecases

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.AddReservationRepository

class AddReservationUseCase {
    lateinit var addReservationRepository: AddReservationRepository
    suspend operator fun invoke(parkingId: String,
                        reservation: Reservation): Result<Boolean> {
        return addReservationRepository.addReservation(parkingId,reservation)
    }
}
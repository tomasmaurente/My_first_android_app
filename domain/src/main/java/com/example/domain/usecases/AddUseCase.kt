package com.example.domain.usecases

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.AddRepository

class AddUseCase {
    lateinit var addReservationRepository: AddRepository

    suspend operator fun invoke(parkingId: String,
                                reservation: Reservation,
                                localDataBase: Boolean): Result<Boolean> {
        return addReservationRepository.addReservation(parkingId,reservation,localDataBase)
    }
    suspend operator fun invoke(lot: Int): Result<Boolean> {
        return addReservationRepository.addLot(lot)
    }
    suspend operator fun invoke(reservation: Reservation): Result<Boolean> {
        return addReservationRepository.addReservation(reservation)
    }
}
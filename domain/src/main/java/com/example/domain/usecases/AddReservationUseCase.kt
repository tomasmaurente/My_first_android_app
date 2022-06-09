package com.example.domain.usecases

import com.example.domain.entities.Lot
import com.example.domain.repositories.AddReservationRepository

class AddReservationUseCase {
    lateinit var addReservationRepository: AddReservationRepository
    operator fun invoke(lot: Int,startDateTime: String,endDateTime: String,authorizationCode: Int ) = addReservationRepository.addReservation(
        lot,startDateTime,endDateTime,authorizationCode)
}
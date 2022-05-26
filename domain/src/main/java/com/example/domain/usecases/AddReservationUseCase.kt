package com.example.domain.usecases

import com.example.domain.entities.ParkingLot
import com.example.domain.repositories.AddReservationRepository
import com.example.domain.repositories.GetLotListRepository

class AddReservationUseCase {
    lateinit var addReservationRepository: AddReservationRepository
    operator fun invoke(id: Int, getFromRemote: Boolean) = addReservationRepository.addReservation(lot,startDateTime,endDateTime,authorizationCode)
}
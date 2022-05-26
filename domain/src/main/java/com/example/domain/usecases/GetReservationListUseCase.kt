package com.example.domain.usecases

import com.example.domain.entities.LotReservation
import com.example.domain.entities.ParkingLot
import com.example.domain.repositories.DeleteReservationRepository
import com.example.domain.repositories.GetReservationListRepository

class GetReservationListUseCase {
    lateinit var getReservationListRepository: GetReservationListRepository
    operator fun invoke(id: Int, getFromRemote: Boolean) = getReservationListRepository.getReservationList(lot)
}
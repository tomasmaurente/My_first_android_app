package com.example.domain.usecases

import com.example.domain.entities.Lot
import com.example.domain.repositories.GetReservationListRepository

class GetReservationListUseCase {
    lateinit var getReservationListRepository: GetReservationListRepository
    operator fun invoke(lot: Lot) = getReservationListRepository.getReservationList(lot)
}
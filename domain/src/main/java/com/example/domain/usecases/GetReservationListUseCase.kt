package com.example.domain.usecases

import com.example.domain.repositories.GetReservationListRepository

class GetReservationListUseCase {
    lateinit var getReservationListRepository: GetReservationListRepository
    suspend operator fun invoke(parkingId: String) = getReservationListRepository.getReservationList(parkingId)
}
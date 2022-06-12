package com.example.domain.usecases

import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.Result
import com.example.domain.repositories.GetReservationListRepository

class GetReservationListUseCase {
    lateinit var getReservationListRepository: GetReservationListRepository
    suspend operator fun invoke(parkingId: String, localDataBase: Boolean): Result<ReservationListModel> {
        return getReservationListRepository.getReservationList(parkingId, localDataBase)
    }
}
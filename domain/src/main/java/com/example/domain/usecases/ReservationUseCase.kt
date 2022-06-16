package com.example.domain.usecases

import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.Result
import com.example.domain.repositories.ReservationRepository

class ReservationUseCase {
    lateinit var getReservationListRepository: ReservationRepository

    suspend operator fun invoke(parkingId: String, localDataBase: Boolean): Result<ReservationListModel> {
        return getReservationListRepository.getReservationList(parkingId, localDataBase)
    }
    suspend operator fun invoke(parkingId: Int): Result<ReservationListModel> {
        return getReservationListRepository.getReservationList(parkingId)
    }

}
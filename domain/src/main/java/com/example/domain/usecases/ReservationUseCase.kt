package com.example.domain.usecases

import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.Result
import com.example.domain.repositories.ReservationRepository

class ReservationUseCase {
    lateinit var getReservationListRepository: ReservationRepository

    suspend operator fun invoke(localDataBase: Boolean): Result<ReservationListModel> {
        return getReservationListRepository.getReservationList(localDataBase)
    }
}
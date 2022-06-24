package com.example.domain.usecases

import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.Result
import com.example.domain.repositories.ReservationRepository

//  FIXME: Rename to GetReservationListUseCase, use cases should always start by verbs
class ReservationUseCase(var getReservationListRepository: ReservationRepository) {

    suspend operator fun invoke(localDataBase: Boolean): Result<ReservationListModel> {
        return getReservationListRepository.getReservationList(localDataBase)
    }
}
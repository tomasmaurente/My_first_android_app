package com.example.domain.usecases

import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.ParkingLotModel
import com.example.domain.entities.Result
import com.example.domain.repositories.LotRepository

class LotUseCase{
    lateinit var lotRepository: LotRepository

    suspend operator fun invoke(localDataBase: Boolean): Result<ParkingLotListModel> {
        return lotRepository.getLotList(localDataBase)
    }
}
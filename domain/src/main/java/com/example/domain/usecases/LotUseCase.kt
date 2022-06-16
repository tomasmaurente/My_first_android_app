package com.example.domain.usecases

import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.ParkingLotModel
import com.example.domain.entities.Result
import com.example.domain.repositories.LotRepository

class LotUseCase{
    lateinit var lotRepository: LotRepository

    suspend operator fun invoke(parkingId: String, localDataBase: Boolean): Result<ParkingLotListModel> {
        return lotRepository.getLotList(parkingId, localDataBase)
    }
    suspend operator fun invoke(parkingId: Int): Result<ParkingLotModel>? {
        return lotRepository.getLot(parkingId)
    }
}
package com.example.domain.usecases

import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.Result
import com.example.domain.repositories.GetLotListRepository

class GetLotListUseCase{
    lateinit var getLotListRepository: GetLotListRepository
    suspend operator fun invoke(parkingId: String): Result<ParkingLotListModel> {
        return getLotListRepository.getLotList(parkingId)
    }
}
package com.example.domain.usecases

import com.example.domain.entities.Lot
import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.ParkingLotModel
import com.example.domain.entities.Result
import com.example.domain.repositories.GetLotListRepository

class GetLotListUseCase {
    lateinit var getLotListRepository: GetLotListRepository
    suspend operator fun invoke(): Result<ParkingLotListModel> {
        val parkingId = "-N0TUDrXZUxA_wbd391E"
        return getLotListRepository.getLotList(parkingId)
    }
}
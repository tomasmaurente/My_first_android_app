package com.example.domain.usecases

import com.example.domain.entities.ParkingLotListModel
import com.example.domain.repositories.LotsRepository

class GetLotListUseCase {
    lateinit var getLotListRepository: LotsRepository
    suspend operator fun invoke(): ParkingLotListModel {
        val parkingId = "https://parking-lot-5aace-default-rtdb.firebaseio.com/"
        return getLotListRepository.getLots(parkingId)
    }
}
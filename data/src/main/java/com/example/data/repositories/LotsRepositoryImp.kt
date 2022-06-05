package com.example.data.repositories

import com.example.data.entities.MapperParkingLotList
import com.example.data.responseObjects.ParkingLotListResponse
import com.example.data.service.LotService
import com.example.domain.entities.ParkingLotListModel
import com.example.domain.repositories.LotsRepository
import com.example.data.service.Result

class LotsRepositoryImp() : LotsRepository {
    lateinit var lotService : LotService
    override suspend fun getLots(parkingId: String): ParkingLotListModel {
        val result =  lotService.getLots(parkingId)
        /*return when (result){
            is Result.Success -> {MapperParkingLotList.toParkingListResponseToModel(result.value!!)}
            is Result.Failure(result.exception)
        }*/
        return MapperParkingLotList.
    }

    //override suspend fun getLots(parkingId: String) = lotService.getLots(parkingId)

}
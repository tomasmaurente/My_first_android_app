package com.example.domain.repositories

import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.ParkingLotModel
import com.example.domain.entities.Result

interface LotRepository {
    suspend fun getLotList(parkingId: String, localDataBase: Boolean): Result<ParkingLotListModel>
    suspend fun getLot(parkingId: Int): Result<ParkingLotModel>
}
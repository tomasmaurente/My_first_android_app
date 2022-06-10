package com.example.domain.repositories

import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.Result

interface GetLotListRepository {
    suspend fun getLotList(parkingId: String, localDataBase: Boolean): Result<ParkingLotListModel>
}
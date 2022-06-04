package com.example.domain.repositories

import com.example.domain.entities.Lot
import com.example.domain.entities.ParkingLotListModel

interface LotsRepository {

    suspend fun getLots(parkingId: String) : ParkingLotListModel

}
package com.example.domain.repositories

import com.example.domain.entities.ParkingLot

interface GetLotListRepository {
    fun getLotList(): List<ParkingLot>
}
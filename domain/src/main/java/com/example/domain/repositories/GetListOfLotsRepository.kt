package com.example.domain.repositories

import com.example.domain.entities.ParkingLot

interface GetListOfLotsRepository {
    fun getListOfLots(): List<ParkingLot>
}
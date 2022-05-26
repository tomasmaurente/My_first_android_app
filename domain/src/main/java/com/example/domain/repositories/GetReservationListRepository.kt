package com.example.domain.repositories

import com.example.domain.entities.LotReservation
import com.example.domain.entities.ParkingLot

interface GetReservationListRepository {
    fun getReservationList(lot: ParkingLot): List<LotReservation>
}
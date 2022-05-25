package com.example.domain.repositories

import com.example.domain.entities.LotReservation
import com.example.domain.entities.ParkingLot

interface GetListOfReservationsRepository {
    fun getListOfReservations(lot: ParkingLot): List<LotReservation>
}
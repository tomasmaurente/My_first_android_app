package com.example.domain.repositories

import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.ReservationModel
import com.example.domain.entities.Result

interface ReservationRepository {
    suspend fun getReservationList(parkingId: String, localDataBase: Boolean): Result<ReservationListModel>
    suspend fun getReservationList(parkingId: Int): Result<ReservationListModel>
}
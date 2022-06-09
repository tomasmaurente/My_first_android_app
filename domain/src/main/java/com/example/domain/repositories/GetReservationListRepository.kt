package com.example.domain.repositories

import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.Result

interface GetReservationListRepository {
    suspend fun getReservationList(parkingId: String): Result<ReservationListModel>
}
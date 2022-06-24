package com.example.data.local_data_base.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entities.Reservation

// FIXME: Rename to ReservationEntity
@Entity(tableName = "reservationList")
data class ReservationRoom(
    @PrimaryKey
    val id: String,
    val authorizationCode: String,
    val start: Long,
    val end: Long,
    val parkingLot: Int
)
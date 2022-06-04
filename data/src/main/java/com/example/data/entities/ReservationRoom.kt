package com.example.data.entities

data class ReservationRoom(val id: String,
                           val startDateInMillis: Long,
                           val endDateTimeInMillis: Long,
                           val authorizationCode: String,
                           val parkingLot: Int
                          )


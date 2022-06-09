package com.example.data.local_data_base.entities

data class ReservationRoom(val id: String,
                           val startDateInMillis: Long,
                           val endDateTimeInMillis: Long,
                           val authorizationCode: String,
                           val parkingLot: Int
                          )


package com.example.domain.entities

import java.io.Serializable

class Reservation(val id: String = "",
                  val startDateTimeInMillis: Long = 0,
                  val endDateTimeInMillis: Long = 0,
                  val authorizationCode: String = "",
                  val parkingLot: Int = 0
)


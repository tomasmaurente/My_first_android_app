package com.example.domain.entities

import java.io.Serializable

class Reservation(val startDateInMillis: Long = 0,
                  val endDateTimeInMillis: Long = 0,
                  val authorizationCode: String = "",
                  val parkingLot: Int = 0
)


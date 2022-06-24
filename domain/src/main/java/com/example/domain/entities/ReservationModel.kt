package com.example.domain.entities

// FIXME: This should be merged with Reservation class
class ReservationModel (var id: String,
                        val authorizationCode: String,
                        val startDate: Long,
                        val endDate: Long,
                        val parkingLot: Int,
                        )
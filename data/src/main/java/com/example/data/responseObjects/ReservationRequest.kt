package com.example.data.responseObjects

data class ReservationRequest(var authorizationCode: String,
                              var startDate: String,
                              var endDate: String,
                              var parkingLot: Int
)

package com.example.data.responseObjects

data class ReservationResponse(var id: String,
                               var authorizationCode: String,
                               var startDate: String,
                               var endDate: String,
                               var parkingLot: Int
                               )



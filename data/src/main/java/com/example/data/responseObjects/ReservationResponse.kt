package com.example.data.responseObjects

data class ReservationResponse(var authorizationCode: String, var strtDate: Long, var endDate: Long, var parkingLot: Int) {
}
package com.example.my_first_app.model.objects

import com.example.my_first_app.model.data_clasesLotReservation.ReservationProvider

data class ParkingLot(
    val spot: Integer,
    val day: String,
    val month_and_year: String,
    val hour: String,
    val reservations: ReservationProvider,
)

package com.example.my_first_app.objects

import com.example.my_first_app.data_clases.Lot_reservations

data class parking_lot(val spot: String,
                       val day: String,
                       val month_and_year: String,
                       val hour: String,
                       val reservations: Lot_reservations
                    )

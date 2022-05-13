package com.example.my_first_app.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_app.R
import com.example.my_first_app.parking_lots

class parking_lot_view_holder (view: View): RecyclerView.ViewHolder(view) {

    val spot_of_reservation = view.findViewById<TextView>(R.id.spot_number)
    val day_of_parking = view.findViewById<TextView>(R.id.day)
    val month_and_year_of_reservation = view.findViewById<TextView>(R.id.month_and_year)
    val hour_of_reservation = view.findViewById<TextView>(R.id.hour_of_day)

    fun render(actualName: parking_lots) {
        spot_of_reservation.text = actualName.spot
        day_of_parking.text = actualName.day
        month_and_year_of_reservation.text = actualName.month_and_year
        hour_of_reservation.text = actualName.hour


    }
}
package com.example.my_first_app.adapters.lotAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Lot
import com.example.domain.entities.Reservation
import com.example.my_first_app.databinding.LayoutLotItemBinding

class ParkingLotViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = LayoutLotItemBinding.bind(view)
    private lateinit var firstReservation: Reservation

    fun render(actualLot: Lot, onClickListener:(Lot)-> Unit) {

        if(actualLot.reservations.isEmpty()){
            binding.markAsFree.text = "@string/freePlaces"
        }
        else{
            firstReservation = actualLot.reservations[0]
            binding.day.text = firstReservation.toString()
            binding.monthAndYear.text = firstReservation.toString()
            binding.hourOfDay.text = firstReservation.toString()
        }

        binding.spotNumber.text = actualLot.spot.toString()

        // Setting click
        itemView.setOnClickListener{onClickListener(actualLot)}
    }
}
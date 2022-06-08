package com.example.my_first_app.adapters.lotAdapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Lot
import com.example.domain.entities.Reservation
import com.example.my_first_app.databinding.LayoutLotItemBinding
import com.example.my_first_app.utils.AppDateFormat
import com.example.my_first_app.utils.StringsUtils

class ParkingLotViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = LayoutLotItemBinding.bind(view)
    private lateinit var firstReservation: Reservation
    private val dateFormat: AppDateFormat = AppDateFormat()

    fun render(actualLot: Lot, onClickListener:(Lot)-> Unit) {

        if(actualLot.reservations.isEmpty()){
            binding.markAsFree.text = StringsUtils.FREE
            binding.day.text = ""
            binding.monthAndYear.text = ""
            binding.hourOfDay.text = ""

            // Setting click listener into the item
            itemView.setOnClickListener{Toast.makeText(binding.fatherLayout.context,"No reservations to show",Toast.LENGTH_SHORT).show()
            }
        }
        else{
            firstReservation = actualLot.reservations[0]
            binding.markAsFree.text = ""
            binding.day.text = dateFormat.dayFormat(firstReservation.startDateTimeInMillis)
            binding.monthAndYear.text = dateFormat.monthYearFormat(firstReservation.startDateTimeInMillis)
            binding.hourOfDay.text = dateFormat.hourFormat(firstReservation.startDateTimeInMillis)

            // Setting click listener into the item
            itemView.setOnClickListener{onClickListener(actualLot)}
        }

        binding.spotNumber.text = actualLot.spot.toString()
    }
}
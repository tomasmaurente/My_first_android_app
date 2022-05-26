package com.example.my_first_app.adapters.lotAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.ParkingLot
import com.example.my_first_app.databinding.LayoutLotItemBinding

class ParkingLotViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = LayoutLotItemBinding.bind(view)



    fun render(actualName: ParkingLot, onClickListener:(ParkingLot)-> Unit) {

        if(actualName.day == "" ){
            binding.markAsFree.text = "@string/freePlaces"
        }
        else{
            binding.day.text = actualName.day
            binding.monthAndYear.text = actualName.month_and_year
            binding.hourOfDay.text = actualName.hour
        }

        binding.spotNumber.text = actualName.spot.toString()

        // Setting click
        itemView.setOnClickListener{onClickListener(actualName)}
    }
}
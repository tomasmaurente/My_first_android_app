package com.example.my_first_app.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_app.R
import com.example.my_first_app.databinding.MainRecycleViewItemWithContentBinding
import com.example.my_first_app.model.objects.ParkingLot

class ParkingLotViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = MainRecycleViewItemWithContentBinding.bind(view)



    fun render(actualName: ParkingLot, onClickListener:(ParkingLot)-> Unit) {
        binding.spotNumber.text = actualName.spot.toString()
        binding.day.text = actualName.day
        binding.monthAndYear.text = actualName.month_and_year
        binding.hourOfDay.text = actualName.hour

        // Setting click
        itemView.setOnClickListener{onClickListener(actualName)}
    }
}
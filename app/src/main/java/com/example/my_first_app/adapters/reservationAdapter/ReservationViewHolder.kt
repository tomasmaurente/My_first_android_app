package com.example.prueba_recycler_view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.LotReservation
import com.example.my_first_app.databinding.LayoutItemReservationBinding

class ReservationViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = LayoutItemReservationBinding.bind(view)

    fun render(actualName: LotReservation, onDeleteButtonListener:(LotReservation) -> Unit){
        binding.endDay.text = actualName.end_day
        binding.endHourOfDay.text = actualName.end_time
        binding.endMonthAndYear.text = actualName.end_month_year

        binding.startDay.text = actualName.start_day
        binding.startHourOfDay.text = actualName.start
        binding.startMonthAndYear.text = actualName.start_month_year

        binding.deleteButton.setOnClickListener{ onDeleteButtonListener(actualName)}

    }
}
package com.example.prueba_recycler_view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Reservation
import com.example.my_first_app.databinding.LayoutItemReservationBinding
import java.util.*

class ReservationViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = LayoutItemReservationBinding.bind(view)

    //private fun changeToString(dateTime: Long): String

    fun render(actualReservation: Reservation, onDeleteButtonListener:(Reservation) -> Unit){
        binding.endDay.text = actualReservation.startDateInMillis.toString()
        binding.endHourOfDay.text = actualReservation.startDateInMillis.toString()
        binding.endMonthAndYear.text = actualReservation.startDateInMillis.toString()

        binding.startDay.text = actualReservation.startDateInMillis.toString()
        binding.startHourOfDay.text = actualReservation.startDateInMillis.toString()
        binding.startMonthAndYear.text = actualReservation.startDateInMillis.toString()

        binding.deleteButton.setOnClickListener{ onDeleteButtonListener(actualReservation)}

    }
}
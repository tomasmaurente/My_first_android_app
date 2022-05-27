package com.example.prueba_recycler_view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Reservation
import com.example.my_first_app.databinding.LayoutItemReservationBinding
import com.example.my_first_app.utils.AppDateFormat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ReservationViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = LayoutItemReservationBinding.bind(view)
    private val dateFormat: AppDateFormat = AppDateFormat()

    fun render(actualReservation: Reservation, onDeleteButtonListener:(Reservation) -> Unit){
        binding.endHourOfDay.text = dateFormat.hourFormat(actualReservation.endDateTimeInMillis)
        binding.endDay.text = dateFormat.dayFormat(actualReservation.endDateTimeInMillis)
        binding.endMonthAndYear.text = dateFormat.monthYearFormat(actualReservation.endDateTimeInMillis)

        binding.startDay.text = dateFormat.dayFormat(actualReservation.startDateInMillis)
        binding.startHourOfDay.text = dateFormat.hourFormat(actualReservation.startDateInMillis)
        binding.startMonthAndYear.text = dateFormat.monthYearFormat(actualReservation.startDateInMillis)

        binding.deleteButton.setOnClickListener{ onDeleteButtonListener(actualReservation)}

    }
}
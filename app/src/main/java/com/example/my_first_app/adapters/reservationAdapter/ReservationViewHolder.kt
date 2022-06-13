package com.example.my_first_app.adapters.reservationAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Reservation
import com.example.my_first_app.databinding.LayoutItemReservationBinding
import com.example.my_first_app.utils.AppDateFormat

class ReservationViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = LayoutItemReservationBinding.bind(view)

    fun render(actualReservation: Reservation, onDeleteButtonListener:(Reservation) -> Unit){
        binding.endHourOfDay.text = AppDateFormat.hourFormat(actualReservation.endDateTimeInMillis)
        binding.endDay.text = AppDateFormat.dayFormat(actualReservation.endDateTimeInMillis)
        binding.endMonthAndYear.text = AppDateFormat.monthYearFormat(actualReservation.endDateTimeInMillis)

        binding.startDay.text = AppDateFormat.dayFormat(actualReservation.startDateTimeInMillis)
        binding.startHourOfDay.text = AppDateFormat.hourFormat(actualReservation.startDateTimeInMillis)
        binding.startMonthAndYear.text = AppDateFormat.monthYearFormat(actualReservation.startDateTimeInMillis)

        binding.deleteButton.setOnClickListener{ onDeleteButtonListener(actualReservation)}

    }
}
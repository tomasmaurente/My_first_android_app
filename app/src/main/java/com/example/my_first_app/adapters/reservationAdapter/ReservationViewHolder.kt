package com.example.my_first_app.adapters.reservationAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Reservation
import com.example.my_first_app.databinding.LayoutItemReservationBinding
import com.example.my_first_app.utils.AppDateFormat

class ReservationViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = LayoutItemReservationBinding.bind(view)
    private val dateFormat: AppDateFormat = AppDateFormat()

    fun render(actualReservation: Reservation, onDeleteButtonListener:(Reservation) -> Unit){
        binding.endHourOfDay.text = dateFormat.hourFormat(actualReservation.endDateTimeInMillis)
        binding.endDay.text = dateFormat.dayFormat(actualReservation.endDateTimeInMillis)
        binding.endMonthAndYear.text = dateFormat.monthYearFormat(actualReservation.endDateTimeInMillis)

        binding.startDay.text = dateFormat.dayFormat(actualReservation.startDateTimeInMillis)
        binding.startHourOfDay.text = dateFormat.hourFormat(actualReservation.startDateTimeInMillis)
        binding.startMonthAndYear.text = dateFormat.monthYearFormat(actualReservation.startDateTimeInMillis)

        binding.deleteButton.setOnClickListener{ onDeleteButtonListener(actualReservation)}

    }
}
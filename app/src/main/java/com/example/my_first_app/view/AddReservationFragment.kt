package com.example.my_first_app.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_first_app.R
import com.example.my_first_app.adapters.ParkingLot_adapter
import com.example.my_first_app.databinding.LayoutAddReservationBinding
import com.example.my_first_app.model.data_clases.ParkingProvider
import com.example.my_first_app.model.objects.ParkingLot
import java.text.SimpleDateFormat
import java.util.*

class AddReservationFragment: Fragment(R.layout.layout_add_reservation) {

    private lateinit var binding: LayoutAddReservationBinding
    private lateinit var tvDatePicker: TextView
    private  lateinit var btnDatePicker: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutAddReservationBinding.bind(view)

        tvDatePicker = binding.tvDate
        btnDatePicker = binding.datePicker

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }

        btnDatePicker.setOnClickListener{
            DatePickerDialog(requireContext(), datePicker, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDatePicker.setText(sdf.format(myFormat))
    }


}
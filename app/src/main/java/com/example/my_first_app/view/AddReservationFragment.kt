package com.example.my_first_app.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutAddReservationBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class AddReservationFragment: Fragment(R.layout.layout_add_reservation) {

    private lateinit var binding: LayoutAddReservationBinding
    private lateinit var tvDatePicker: TextView
    private  lateinit var btnDatePicker: EditText
    private var timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = LayoutAddReservationBinding.bind(view)

        tvDatePicker = binding.startDateTimeText
        btnDatePicker = binding.startDateTimeButton

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)
        }

        // this button calls a date picker fragment
        binding.startDateTimeButton.setOnClickListener{
            // showDatePicker() this method shows the calendar in fullscreen, we are looking for a dialog fragment
            DatePickerDialog(requireContext(), datePicker, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // this button calls a test fragment
        binding.endDateTimeButton.setOnClickListener {
            val newFragment = DialogFragmentTest.newInstance()
            newFragment.show(parentFragmentManager, "dialog")
        }

        binding.backButton.setOnClickListener {
            binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to parkingLotsFragment
        }

    }
    // useless method, this method just pick one time, not a range
    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDatePicker.text = (sdf.format(myCalendar.time))
    }

    private fun showDatePicker(){
        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select Date")
            .build()
        dateRangePicker.show(
            parentFragmentManager,
            "date_range_picker"
        )

        dateRangePicker.addOnPositiveButtonClickListener { datePicked ->
            val startDate = datePicked.first
            val endDate = datePicked.second
            //val actualDate = LocalDateTime.now() this does not work
            //Toast.makeText(activity,"$startDate $endDate", Toast.LENGTH_SHORT).show()  this was a practice

            if (startDate != null && endDate != null){  // this sentence need to compare if actualDate is equals or previous to startDate, and
                                                        // if in the range date selected there isn´t already a reservation
                binding.startDateTimeText.text = "La fecha de inicio es: "+ convertLongToDate(startDate)+", y la final es:"+convertLongToDate(endDate)
            }
        }
    }

    private fun convertLongToDate(incommingDate:Long):String {
        val date = Date(incommingDate)
        val myFormat = "dd-MM-yyyy"
        val format = SimpleDateFormat(myFormat, Locale.getDefault())

        return format.format(date)

    }
}


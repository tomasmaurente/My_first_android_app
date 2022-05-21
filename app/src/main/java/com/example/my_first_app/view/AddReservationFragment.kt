package com.example.my_first_app.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_first_app.R
import com.example.my_first_app.adapters.ParkingLotAdapter
import com.example.my_first_app.databinding.LayoutAddReservationBinding
import com.example.my_first_app.model.data_clases.ParkingProvider
import com.example.my_first_app.model.objects.ParkingLot
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

        btnDatePicker.setOnClickListener{
            showDatePicker()
            //DatePickerDialog(requireContext(), datePicker, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.startDateTimeButton.setOnClickListener {
            PurchaseConfirmationDialogFragment().show(
                childFragmentManager, PurchaseConfirmationDialogFragment.TAG
            )
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


    /*private fun onTimePicker() {
        val timePicker = TimePickerDialog(
            activity, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute -> },
            now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
        )

        timePicker.show()

    }    */

    /*private fun showDialog() {
        val timeRangePicker = TimeRangePickerDialog.Builder()
            .setTimeRange(10, 20, 16, 40)
            .setTimeInterval(20)
            .setOnDayMode(false)
            .setOnTimeRangeSelectedListener { tv_selected_range.text = it.readableTimeRange }
            .build()
            .show(supportFragmentManager)
    }

    private fun showBottomSheetDialog() {
        TimeRangePickerBottomSheet.getInstance().show(this)
    }*/






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


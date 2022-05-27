package com.example.my_first_app.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutAddReservationBinding
import com.example.my_first_app.databinding.LayoutReservationsBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class AddReservationFragment: Fragment(R.layout.layout_add_reservation) {

    private lateinit var binding: LayoutAddReservationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = LayoutAddReservationBinding.bind(view)

        // Back button
        binding.backButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to reservationsFragment
        }

        // Spinner
        val spinner = binding.root.findViewById<Spinner>(R.id.lotListButton)
        val listOfLots = resources.getStringArray(R.array.lotsNumbers)
        val adapter = activity?.let { ArrayAdapter(it,R.layout.layout_drop_down_item,listOfLots) }

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                ViewParent: AdapterView<*>?,
                view: View?,
                position: Int,
                p3: Long,
            ) {
                binding.lotListText.text = "hello World $position" 
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.lotListText.text = "bye World"
            }
        }

        // Start Date and time picker
        binding.startDateButton.setOnClickListener{
            pickStartDateTime()
        }

        // End Date and time picker
        binding.endDateTimeButton.setOnClickListener{
            pickEndDateTime()
        }

        // Authorization code picker
        binding.authorizationCodeButton.setOnClickListener{
            binding.authorizationCodeText.text = binding.authorizationCodeButton.text
        }

        // Save button
        binding.saveButton.setOnClickListener{
            Toast.makeText(activity,"You made it!!!",Toast.LENGTH_SHORT).show()
            // Code to Add a reservation
            binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to reservationsFragment
        }
    }

    private fun pickStartDateTime( ) {
        val myCalendar = Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener { view,hours,minutes ->
            myCalendar.set(Calendar.MINUTE, minutes)
            myCalendar.set(Calendar.HOUR, hours)
            updateStartTimeLable(myCalendar)
        }
        val dateListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            updateStartDateLable(myCalendar)
        }

        TimePickerDialog(
            activity,
            timeListener,
            myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE),
            false
        ).show()

        DatePickerDialog(
            requireContext(),
            dateListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateStartDateLable(myCalendar: Calendar) {
        val myFormat = " dd-MM-yyyy "
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.startTimeText.text = (sdf.format(myCalendar.time))
    }

    private fun updateStartTimeLable(myCalendar: Calendar) {
        val myFormat = " hh:mm "
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.startDateText.text = (sdf.format(myCalendar.time))
    }

    private fun pickEndDateTime( ) {
        val myCalendar = Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener { view,hours,minutes ->
            myCalendar.set(Calendar.MINUTE, minutes)
            myCalendar.set(Calendar.HOUR, hours)
            updateEndTimeLable(myCalendar)
        }
        val dateListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            updateEndDateLable(myCalendar)
        }

        TimePickerDialog(
            activity,
            timeListener,
            myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE),
            false
        ).show()

        DatePickerDialog(
            requireContext(),
            dateListener,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateEndDateLable(myCalendar: Calendar) {
        val myFormat = " dd-MM-yyyy "
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.endTimeText.text = (sdf.format(myCalendar.time))
    }

    private fun updateEndTimeLable(myCalendar: Calendar) {
        val myFormat = " hh:mm "
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.endDateText.text = (sdf.format(myCalendar.time))
    }
}


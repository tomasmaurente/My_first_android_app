package com.example.my_first_app.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.entities.Reservation
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutAddReservationBinding
import java.text.SimpleDateFormat
import java.util.*

class AddReservationFragment: Fragment(R.layout.layout_add_reservation) {

    private lateinit var binding: LayoutAddReservationBinding
    private var lot: Int = 0
    private lateinit var startDateTime: Calendar
    private lateinit var endDateTime: Calendar
    private lateinit var authorizationCode: String

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
                lot = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        // Start Date and time picker
        binding.startDateTimeButton.setOnClickListener{
            pickStartDateTime()
        }

        // End Date and time picker
        binding.endDateTimeButton.setOnClickListener{
            pickEndDateTime()
        }

        // Authorization code picker
        binding.authorizationCodeButton.setOnClickListener{
            authorizationCode = binding.authorizationCodeButton.text.toString()
        }

        // Save button
        binding.saveButton.setOnClickListener{
            if( startDateTime != null && endDateTime != null && lot != 0 && authorizationCode != null){
                //val getReservationListRepositoryImp = GetReservationListRepositoryImp()
                //getReservationListRepositoryImp.addReservation(Reservation(startDateTime.timeInMillis,endDateTime.timeInMillis,authorizationCode, lot))
                // Back to main
                Toast.makeText(activity,"You made it!!!",Toast.LENGTH_SHORT).show()
                binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to reservationsFragment
            } else {
                Toast.makeText(activity, "You have to complete all the fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun pickStartDateTime( ) {
        startDateTime = Calendar.getInstance()

        val timeListener = TimePickerDialog.OnTimeSetListener { view,hours,minutes ->
            startDateTime.set(Calendar.MINUTE, minutes)
            startDateTime.set(Calendar.HOUR, hours)

            val myFormat = " dd-MM-yyyy hh:mm "
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
            (sdf.format(startDateTime.time))

            binding.startDateTimeButton.hint = (sdf.format(startDateTime.time))
        }

        val dateListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            startDateTime.set(Calendar.YEAR, year)
            startDateTime.set(Calendar.MONTH, month)
            startDateTime.set(Calendar.DAY_OF_MONTH, day)
        }

        TimePickerDialog(
            activity,
            timeListener,
            startDateTime.get(Calendar.HOUR_OF_DAY),
            startDateTime.get(Calendar.MINUTE),
            false
        ).show()

        DatePickerDialog(
            requireContext(),
            dateListener,
            startDateTime.get(Calendar.YEAR),
            startDateTime.get(Calendar.MONTH),
            startDateTime.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun pickEndDateTime( ) {
        endDateTime = Calendar.getInstance()

        val timeListener = TimePickerDialog.OnTimeSetListener { view,hours,minutes ->
            endDateTime.set(Calendar.MINUTE, minutes)
            endDateTime.set(Calendar.HOUR, hours)

            val myFormat = " dd-MM-yyyy hh:mm "
            val sdf = SimpleDateFormat(myFormat, Locale.UK)
            (sdf.format(endDateTime.time))

            binding.endDateTimeButton.hint = (sdf.format(endDateTime.time))
        }
        val dateListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            endDateTime.set(Calendar.YEAR, year)
            endDateTime.set(Calendar.MONTH, month)
            endDateTime.set(Calendar.DAY_OF_MONTH, day)
        }

        TimePickerDialog(
            activity,
            timeListener,
            endDateTime.get(Calendar.HOUR_OF_DAY),
            endDateTime.get(Calendar.MINUTE),
            false
        ).show()

        DatePickerDialog(
            requireContext(),
            dateListener,
            endDateTime.get(Calendar.YEAR),
            endDateTime.get(Calendar.MONTH),
            endDateTime.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}


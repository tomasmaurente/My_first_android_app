package com.example.my_first_app.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.domain.utils.AddPossibilities
import com.example.domain.entities.Reservation
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutAddReservationBinding
import com.example.my_first_app.utils.AppDateFormat
import com.example.my_first_app.viewModel.AppViewModelProvider
import com.example.my_first_app.viewModel.addViewModelPackage.AddViewModel
import java.util.*

class AddReservationFragment: Fragment(R.layout.layout_add_reservation) {

    private lateinit var binding: LayoutAddReservationBinding
    private var lot: Int? = null
    private lateinit var startDateTime: Calendar
    private lateinit var endDateTime: Calendar
    private lateinit var authorizationCode: String

    private val viewModel by lazy{
        activity?.let { AppViewModelProvider(it).get(AddViewModel::class.java) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = LayoutAddReservationBinding.bind(view)

        // Back button
        binding.backButton.setOnClickListener{
            binding.root.findNavController().popBackStack() // switching screen to previous fragment
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
                lot = position - 1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        // Start Date and time picker
        binding.startDateTimeButton.setOnClickListener{
            dateTimePicker(true)
        }

        // End Date and time picker
        binding.endDateTimeButton.setOnClickListener{
            dateTimePicker(false)
        }

        // Authorization code picker
        binding.authorizationCodeButton.setOnClickListener{
            authorizationCode = binding.authorizationCodeButton.text.toString()
        }

        // Save button
        binding.saveButton.setOnClickListener{
             lot?.let{ lot ->
                if( this::startDateTime.isInitialized &&
                    this::endDateTime.isInitialized &&
                    this::authorizationCode.isInitialized &&
                    lot > -1) {
                    viewModel!!.addReservation(
                        Reservation(
                            "",
                            startDateTime!!.timeInMillis,
                            endDateTime!!.timeInMillis,
                            authorizationCode!!,
                            lot
                        )
                    )
                } else {
                    Toast.makeText(activity, "You have to complete all the fields", Toast.LENGTH_SHORT).show()
                }
             }
        }
        viewModel?.addReservationState?.observe(viewLifecycleOwner){ successfulAddition ->
            when (successfulAddition) {
                AddPossibilities.Successful -> {
                    Toast.makeText(activity, "Your reservation has been saved", Toast.LENGTH_SHORT).show()
                    binding.root.findNavController().navigate(R.id.action_addReservationsFragment_to_parkingLotsFragment)
                    viewModel!!.setWaitingState()
                }
                AddPossibilities.Occupied -> {
                    Toast.makeText(activity, "The dates you have chosen are already taken", Toast.LENGTH_SHORT).show()
                    viewModel!!.setWaitingState()
                }
                AddPossibilities.IncorrectParameters -> {
                    Toast.makeText(activity, "You have to complete all the fields", Toast.LENGTH_SHORT).show()
                    viewModel!!.setWaitingState()
                }
                AddPossibilities.Waiting -> {}
                else -> {
                    Toast.makeText(activity, "Unexpected error occurred", Toast.LENGTH_SHORT).show()
                    viewModel!!.setWaitingState()
                }
            }
        }
    }

    private fun dateTimePicker(isStartDateTime: Boolean) {
        var dateTime = Calendar.getInstance()

        val timeListener = TimePickerDialog.OnTimeSetListener { view,hours,minutes ->
            dateTime.set(Calendar.MINUTE, minutes)
            dateTime.set(Calendar.HOUR, hours)

            if(isStartDateTime){

                startDateTime = dateTime
                binding.startDateTimeButton.hint = AppDateFormat.completeFormat(startDateTime.timeInMillis)

            } else {
                endDateTime = dateTime
                binding.endDateTimeButton.hint = AppDateFormat.completeFormat(endDateTime.timeInMillis)
            }
        }

        val dateListener = DatePickerDialog.OnDateSetListener { _,year,month,day ->
            dateTime.set(Calendar.YEAR, year)
            dateTime.set(Calendar.MONTH, month)
            dateTime.set(Calendar.DAY_OF_MONTH, day)
        }

        TimePickerDialog(
            activity,
            timeListener,
            dateTime.get(Calendar.HOUR_OF_DAY),
            dateTime.get(Calendar.MINUTE),
            false
        ).show()

        DatePickerDialog(
            requireContext(),
            dateListener,
            dateTime.get(Calendar.YEAR),
            dateTime.get(Calendar.MONTH),
            dateTime.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}


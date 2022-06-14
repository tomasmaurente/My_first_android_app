package com.example.my_first_app.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.domain.entities.Reservation
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutAddReservationBinding
import com.example.my_first_app.utils.AppDateFormat
import com.example.my_first_app.viewModel.addViewModelPackage.AddViewModel
import com.example.my_first_app.viewModel.addViewModelPackage.AddViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddReservationFragment: Fragment(R.layout.layout_add_reservation) {

    private lateinit var binding: LayoutAddReservationBinding
    private var lot: Int = -1
    private lateinit var startDateTime: Calendar
    private lateinit var endDateTime: Calendar
    private lateinit var authorizationCode: String

    private val viewModel by lazy{
        activity?.let { AddViewModelProvider(it).get(AddViewModel::class.java) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        binding = LayoutAddReservationBinding.bind(view)

        // Back button
        binding.backButton.setOnClickListener{
            binding.root.findNavController().popBackStack() // switching screen to reservationsFragment
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
            if( this::startDateTime.isInitialized &&
                this::endDateTime.isInitialized &&
                this::authorizationCode.isInitialized &&
                lot > 0) {
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
                Toast.makeText(activity,"Please fill all the fields before saving",Toast.LENGTH_LONG).show()
            }
        }

        viewModel?.addReservationState?.observe(viewLifecycleOwner){ successfulAddition ->
            if (successfulAddition){
                Toast.makeText(activity, "Your reservation has been saved", Toast.LENGTH_SHORT).show()
                binding.root.findNavController().popBackStack()
            } else {
                Toast.makeText(activity, "You have to complete all the fields", Toast.LENGTH_SHORT).show()
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
                binding.endDateTimeButton.hint = AppDateFormat.completeFormat(startDateTime.timeInMillis)
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

    /*private fun pickEndDateTime( ) {
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
    }*/
}


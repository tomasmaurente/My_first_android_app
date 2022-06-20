package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.Lot
import com.example.domain.entities.Reservation
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutReservationsBinding
import com.example.my_first_app.adapters.reservationAdapter.ReservationAdapter
import com.example.my_first_app.utils.DeleteDialogCallBack
import com.example.my_first_app.viewModel.AppViewModelProvider
import com.example.my_first_app.viewModel.reservationsViewModelPackage.ReservationViewModel

class ReservationFragment: Fragment(R.layout.layout_reservations), DeleteDialogCallBack {

    private lateinit var binding: LayoutReservationsBinding

    private lateinit var lotSelected: Lot

    private val viewModel by lazy{
        AppViewModelProvider(activity).get(ReservationViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutReservationsBinding.bind(view)
        binding.recyclerReservations.layoutManager = LinearLayoutManager(activity)

        arguments?.let { lotSelected = it.getSerializable("lot") as Lot }
        binding.lotNumber.text = lotSelected.spot.toString()
        initRecyclerView(lotSelected.reservations)

        binding.imageButton.setOnClickListener {
            binding.root.findNavController().popBackStack()
        }

        binding.floatingAddButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_addReservationFragment)  // switching screen to reservationsFragment

        }
    }

    private fun initRecyclerView(reservationList: List<Reservation>){
        binding.recyclerReservations.adapter = ReservationAdapter(reservationList){ reservation ->
            onButtonDeleteSelected(
                reservation
            )
        }
    }

    private fun onButtonDeleteSelected(reservation: Reservation){
            // Create the fragment and show it as a dialog.
        val newFragment: DialogFragment = DeleteDialogFragment.newInstance(this, reservation)
        newFragment.show(parentFragmentManager, "dialog")
    }

    override fun onDeleteClicked(authorizationCode: String, reservation: Reservation) {
        viewModel.deleteReservation(authorizationCode,reservation)

        viewModel.deletedSuccessfully.observe(viewLifecycleOwner) { deletedSuccessfully ->
            if (deletedSuccessfully) {
                binding.root.findNavController()
                    .navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)
                Toast.makeText(
                    activity,
                    "Your reservation has been deleted",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(activity, "Incorrect authorization code", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
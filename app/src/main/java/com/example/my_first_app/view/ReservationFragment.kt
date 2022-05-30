package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.entities.Lot
import com.example.domain.entities.Reservation
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutReservationsBinding
import com.example.my_first_app.adapters.reservationAdapter.ReservationAdapter
import com.example.my_first_app.databinding.LayoutDeleteDialogBinding

class ReservationFragment: Fragment(R.layout.layout_reservations) {

    private lateinit var binding: LayoutReservationsBinding
    private lateinit var getReservationListRepositoryImp: GetReservationListRepositoryImp
    private lateinit var lotSelected: Lot

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding = LayoutReservationsBinding.bind(view)
            getReservationListRepositoryImp = GetReservationListRepositoryImp()
            arguments?.let { lotSelected = it.getSerializable("lot") as Lot }
            binding.lotNumber.text = lotSelected.spot.toString()

            initRecyclerView()

            binding.imageButton.setOnClickListener {
                onBackButtonSelected()
            }

            binding.floatingAddButton.setOnClickListener{
                binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_addReservationFragment)  // switching screen to reservationsFragment
            }


    }

    // getReservationListRepositoryImp.getReservationList(lotSelected)

    private fun initRecyclerView(){
        binding.recyclerReservations.layoutManager = LinearLayoutManager(activity)
        binding.recyclerReservations.adapter = ReservationAdapter(lotSelected.reservations){ reservation ->
            onButtonDeleteSelected(
                reservation
            )
        }
    }

    private fun onButtonDeleteSelected(reservation: Reservation){
            // Create the fragment and show it as a dialog.
        val newFragment: DialogFragment = DeleteDialogFragment.newInstance()
        newFragment.show(parentFragmentManager, "dialog")
    }

    private fun onBackButtonSelected(){
        binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to parkingLotsFragment
    }
}
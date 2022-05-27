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

class ReservationFragment: Fragment(R.layout.layout_reservations) {

    private lateinit var binding: LayoutReservationsBinding
    private lateinit var getReservationListRepositoryImp: GetReservationListRepositoryImp
    private lateinit var exampleLot: Lot
    private var lotSpot: Int = 0

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding = LayoutReservationsBinding.bind(view)
            getReservationListRepositoryImp = GetReservationListRepositoryImp()
            exampleLot = Lot(4,listOf())
            arguments?.let { lotSpot = it.getInt("lotSpot", 0) }
            binding.lotNumber.text = lotSpot.toString()

            initRecyclerView()

            binding.imageButton.setOnClickListener {
                onBackButtonSelected()
            }

            binding.floatingAddButton.setOnClickListener{
                binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_addReservationFragment)  // switching screen to reservationsFragment
            }
    }

    fun initRecyclerView(){
        binding.recyclerReservations.layoutManager = LinearLayoutManager(activity)
        binding.recyclerReservations.adapter = ReservationAdapter(getReservationListRepositoryImp.getReservationList(exampleLot)){ reservation ->
            onButtonDeleteSelected(
                reservation
            )
        }
    }

    fun onButtonDeleteSelected(reservation: Reservation){
            // Create the fragment and show it as a dialog.
        val newFragment: DialogFragment = DeleteDialogFragment.newInstance()
        newFragment.show(parentFragmentManager, "dialog")
    }

    fun onBackButtonSelected(){
        binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to parkingLotsFragment
    }
}
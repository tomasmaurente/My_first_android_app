package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_first_app.R
import com.example.my_first_app.adapters.ParkingLot_adapter
import com.example.my_first_app.databinding.LayoutParkingLotsBinding
import com.example.my_first_app.databinding.LayoutReservationsBinding
import com.example.my_first_app.model.data_clases.ParkingProvider
import com.example.my_first_app.model.data_clasesLotReservation.ReservationProvider
import com.example.my_first_app.model.objects.LotReservation
import com.example.my_first_app.model.objects.ParkingLot
import com.example.prueba_recycler_view.adapter.ReservationsAdapter

class ReservationsFragment: Fragment(R.layout.layout_reservations) {

    private lateinit var binding: LayoutReservationsBinding

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutReservationsBinding.bind(view)
        initRecyclerView()

        binding.imageButton.setOnClickListener {
            onBackButtonSelected()
        }
    }

    fun initRecyclerView(){
        binding.recyclerReservations.layoutManager = LinearLayoutManager(activity)
        binding.recyclerReservations.adapter = ReservationsAdapter(ReservationProvider.reservations){reservation ->
            onReservationSelected(
                reservation
            )
        }
    }

    fun onReservationSelected(reservation: LotReservation){
        Toast.makeText(activity,"Hello World", Toast.LENGTH_SHORT).show()
        //binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to parkingLotsFragment
    }

    fun onBackButtonSelected(){
        binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_parkingLotsFragment)  // switching screen to parkingLotsFragment

    }


}
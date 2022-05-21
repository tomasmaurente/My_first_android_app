package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_first_app.R
import com.example.my_first_app.adapters.ParkingLotAdapter
import com.example.my_first_app.model.data_clases.ParkingProvider
import com.example.my_first_app.databinding.LayoutParkingLotsBinding
import com.example.my_first_app.model.objects.ParkingLot

class LotsFragment: Fragment(R.layout.layout_parking_lots) {

    private lateinit var binding: LayoutParkingLotsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutParkingLotsBinding.bind(view)
        val recyclerViewBinding = binding.mainRecyclerView
        initRecyclerView()

        binding.floatingAddButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_parkingLotsFragment_to_addReservationFragment)  // switching screen to reservationsFragment
        }
    }

    private fun initRecyclerView(){
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.mainRecyclerView.adapter = ParkingLotAdapter(ParkingProvider.spots) { parkingSpot ->
            onParkingSpotSelected(
                parkingSpot
            )
        }
    }

    private fun onParkingSpotSelected(parkingLots: ParkingLot){
        Toast.makeText(activity,parkingLots.spot.toString(), Toast.LENGTH_SHORT).show()
        binding.root.findNavController().navigate(R.id.action_parkingLotsFragment_to_reservationsFragment)  // switching screen to reservationsFragment
    }
}

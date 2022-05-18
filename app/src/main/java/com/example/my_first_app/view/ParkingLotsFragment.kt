package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_first_app.R
import com.example.my_first_app.adapters.parking_lot_adapter
import com.example.my_first_app.model.data_clases.parking_provider
import com.example.my_first_app.databinding.FragmentParkingLotsBinding
import com.example.my_first_app.model.objects.parking_lot

class ParkingLotsFragment: Fragment(R.layout.fragment_parking_lots) {

    private lateinit var binding: FragmentParkingLotsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentParkingLotsBinding.bind(view)
        val recyclerViewBinding = binding.mainRecyclerView
        initRecyclerView()
    }

    fun initRecyclerView(){
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.mainRecyclerView.adapter = parking_lot_adapter(parking_provider.spots) { parkingSpot ->
            onParkingSpotSelected(
                parkingSpot
            )
        }
    }

    fun onParkingSpotSelected(parkingLots: parking_lot){
        Toast.makeText(activity,parkingLots.spot.toString(), Toast.LENGTH_SHORT).show()
        binding.root.findNavController().navigate(R.id.firstScreenToSecondScreen) // Cambio de pantalla
                                                                                  // binding.root es la propia vista
    }
}

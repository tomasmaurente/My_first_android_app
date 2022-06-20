package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entities.Lot
import com.example.my_first_app.R
import com.example.my_first_app.adapters.lotAdapter.ParkingLotAdapter
import com.example.my_first_app.databinding.LayoutParkingLotsBinding
import com.example.my_first_app.viewModel.AppViewModelProvider
import com.example.my_first_app.viewModel.lotViewModelPackage.LotViewModel

class LotFragment: Fragment(R.layout.layout_parking_lots) {

    private lateinit var binding: LayoutParkingLotsBinding
    private var lotList: List<Lot> = listOf()

    private val viewModel by lazy{
        AppViewModelProvider(activity).get(LotViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutParkingLotsBinding.bind(view)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)

        binding.floatingAddButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_parkingLotsFragment_to_addReservationFragment)  // switching screen to reservationsFragment
        }

        viewModel.createParkingState(true)
        viewModel.parkingState.observe(viewLifecycleOwner){
            lotList = it
            updateProgressBar(viewModel.getNumberOfFreeLots(lotList))
            updateRecyclerView(lotList)
        }
        viewModel.createParkingState(false)
    }

    private fun updateRecyclerView(newLotList: List<Lot>){
        binding.mainRecyclerView.adapter = ParkingLotAdapter(newLotList) { parkingSpot ->
            onParkingSpotSelected(
                parkingSpot
            )
        }
    }

    private fun updateProgressBar(parkingAvailability: Int){
        binding.progressBar.max = lotList.size
        binding.progressBar.progress = lotList.size - parkingAvailability
        binding.numberFreePlaces.text = parkingAvailability.toString()
        binding.numberBusyPlaces.text = (lotList.size - parkingAvailability).toString()
    }

    private fun onParkingSpotSelected(parkingLot: Lot){
        val navController = binding.root.findNavController()
        val bundle = Bundle()
        bundle.putSerializable("lot", parkingLot)
        navController.navigate(R.id.action_parkingLotsFragment_to_reservationsFragment,bundle)
    }
}


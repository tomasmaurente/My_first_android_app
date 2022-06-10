package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.repositories.GetLotListRepositoryImp
import com.example.domain.entities.Lot
import com.example.domain.entities.ParkingLotModel
import com.example.my_first_app.R
import com.example.my_first_app.adapters.lotAdapter.ParkingLotAdapter
import com.example.my_first_app.databinding.LayoutParkingLotsBinding
import com.example.my_first_app.utils.Event
import com.example.my_first_app.viewModel.lotViewModelPackage.LotViewModel
import com.example.my_first_app.viewModel.lotViewModelPackage.LotViewModelProvider

class LotFragment: Fragment(R.layout.layout_parking_lots) {

    private lateinit var binding: LayoutParkingLotsBinding
    private lateinit var lotList: List<Lot>
    private val parkingId: String = "-N0TUDrXZUxA_wbd391E"

    private val viewModel by lazy{
        LotViewModelProvider(activity).get(LotViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutParkingLotsBinding.bind(view)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)

        lotList = listOf()
        viewModel.createParkingState(parkingId)
        viewModel.parkingState.observe(viewLifecycleOwner){
            lotList = it
            updateProgressBar(viewModel.getNumberOfFreeLots(lotList))
            updateRecyclerView(lotList)
        }

        binding.floatingAddButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_parkingLotsFragment_to_addReservationFragment)  // switching screen to reservationsFragment
        }
    }

    private fun initRecyclerView(){
        binding.mainRecyclerView.adapter = ParkingLotAdapter(lotList) { parkingSpot ->
            onParkingSpotSelected(
                parkingSpot
            )
        }
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


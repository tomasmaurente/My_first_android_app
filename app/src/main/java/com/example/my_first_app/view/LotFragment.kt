package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.repositories.GetLotListRepositoryImp
import com.example.domain.entities.Lot
import com.example.my_first_app.R
import com.example.my_first_app.adapters.lotAdapter.ParkingLotAdapter
import com.example.my_first_app.databinding.LayoutParkingLotsBinding

class LotFragment: Fragment(R.layout.layout_parking_lots) {

    private lateinit var binding: LayoutParkingLotsBinding
    private var getLotListRepositoryImp: GetLotListRepositoryImp = GetLotListRepositoryImp()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutParkingLotsBinding.bind(view)
        val recyclerViewBinding = binding.mainRecyclerView
        val lotList = getLotListRepositoryImp.getLotList()

        var parkingAvailability = lotList.size
        lotList.forEach(){
            if (it.reservations.isEmpty()){
                parkingAvailability --
            }
        }
        binding.progressBar.max = lotList.size
        binding.progressBar.progress = parkingAvailability
        binding.numberFreePlaces.text = (lotList.size - parkingAvailability).toString()
        binding.numberBusyPlaces.text = parkingAvailability.toString()

        initRecyclerView()

        binding.floatingAddButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_parkingLotsFragment_to_addReservationFragment)  // switching screen to reservationsFragment
        }
    }

    private fun initRecyclerView(){
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.mainRecyclerView.adapter = ParkingLotAdapter(getLotListRepositoryImp.getLotList()) { parkingSpot ->
            onParkingSpotSelected(
                parkingSpot
            )
        }
    }

    private fun onParkingSpotSelected(parkingLot: Lot){
        //Toast.makeText(activity,parkingLot.spot.toString(), Toast.LENGTH_SHORT).show()
        val navController = binding.root.findNavController()
        val bundle = Bundle()
        //bundle.putInt("lotSpot",parkingLot.spot)
        bundle.putSerializable("lot", parkingLot)
        navController.navigate(R.id.action_parkingLotsFragment_to_reservationsFragment,bundle)
    }
}

package com.example.my_first_app.view

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.repositories.GetLotListRepositoryImp
import com.example.domain.entities.Lot
import com.example.domain.entities.Reservation
import com.example.my_first_app.R
import com.example.my_first_app.adapters.lotAdapter.ParkingLotAdapter
import com.example.my_first_app.databinding.LayoutParkingLotsBinding
import com.example.my_first_app.utils.Event
import com.example.my_first_app.viewModel.lotViewModelPackage.LotViewModel
import com.example.my_first_app.viewModel.lotViewModelPackage.LotViewModelProvider
import com.example.my_first_app.viewModel.reservationsViewModelPackage.ReservationViewModel
import com.example.my_first_app.viewModel.reservationsViewModelPackage.ReservationViewModelProvider

class LotFragment: Fragment(R.layout.layout_parking_lots) {

    private lateinit var binding: LayoutParkingLotsBinding

    private val viewModel by lazy{
        LotViewModelProvider(activity).get(LotViewModel::class.java)
    }
    private var getLotListRepositoryImp: GetLotListRepositoryImp = GetLotListRepositoryImp()
    private lateinit var lotList: List<Lot>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutParkingLotsBinding.bind(view)
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(activity)

        val liveDataObserver: Observer<Event<List<Lot>>> = Observer<Event<List<Lot>>> {
            updateRecyclerView(it.peekContent())
            lotList = it.peekContent()
        }

        lotList = getLotListRepositoryImp.getLotList()

        activity?.let { viewModel.listParkingLotState.observe(it, liveDataObserver) }

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
        binding.mainRecyclerView.adapter = ParkingLotAdapter(getLotListRepositoryImp.getLotList()) { parkingSpot ->
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


    private fun onParkingSpotSelected(parkingLot: Lot){
        //Toast.makeText(activity,parkingLot.spot.toString(), Toast.LENGTH_SHORT).show()
        val navController = binding.root.findNavController()
        val bundle = Bundle()
        //bundle.putInt("lotSpot",parkingLot.spot)
        bundle.putSerializable("lot", parkingLot)
        navController.navigate(R.id.action_parkingLotsFragment_to_reservationsFragment,bundle)
    }
}

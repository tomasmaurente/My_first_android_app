package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.entities.Lot
import com.example.domain.entities.Reservation
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutReservationsBinding
import com.example.my_first_app.adapters.reservationAdapter.ReservationAdapter
import com.example.my_first_app.utils.Event
import com.example.my_first_app.viewModel.reservationsViewModelPackage.ReservationViewModel
import com.example.my_first_app.viewModel.reservationsViewModelPackage.ReservationViewModelProvider

class ReservationFragment: Fragment(R.layout.layout_reservations) {

    private lateinit var binding: LayoutReservationsBinding
    private lateinit var getReservationListRepositoryImp: GetReservationListRepositoryImp
    private lateinit var mutableReservationList : List<Reservation>

    private lateinit var lotSelected: Lot

    private val viewModel by lazy{
        ReservationViewModelProvider(activity).get(ReservationViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutReservationsBinding.bind(view)
        binding.recyclerReservations.layoutManager = LinearLayoutManager(activity)
        getReservationListRepositoryImp = GetReservationListRepositoryImp()
        arguments?.let { lotSelected = it.getSerializable("lot") as Lot }
        binding.lotNumber.text = lotSelected.spot.toString()

        val liveDataObserver: Observer<Event<List<Reservation>>> = Observer<Event<List<Reservation>>> {
            updateRecyclerView(it.peekContent())
            mutableReservationList = it.peekContent()
        }
        activity?.let { viewModel.listReservationState.observe(it, liveDataObserver) }

//  borrarr
        lotSelected = Lot()

        initRecyclerView()

        binding.imageButton.setOnClickListener {
            onBackButtonSelected()
        }

        binding.floatingAddButton.setOnClickListener{
            binding.root.findNavController().navigate(R.id.action_reservationsFragment_to_addReservationFragment)  // switching screen to reservationsFragment

        }
    }

    private fun updateRecyclerView(newReservationList: List<Reservation>) {
        binding.recyclerReservations.adapter = ReservationAdapter(newReservationList){ reservation ->
            onButtonDeleteSelected(
                reservation
            )
        }
    }

    private fun initRecyclerView(){
        binding.recyclerReservations.adapter = ReservationAdapter(getReservationListRepositoryImp.getReservationList(lotSelected)){ reservation ->
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
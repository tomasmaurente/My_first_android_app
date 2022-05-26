package com.example.my_first_app.viewModel.reservationsViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.LotReservation
import com.example.domain.entities.ParkingLot
import com.example.domain.usecases.GetLotListUseCase
import com.example.domain.usecases.GetReservationListUseCase
import com.example.my_first_app.utils.Event

class ReservationViewModel (val getLotReservationList: GetReservationListUseCase) : ViewModel() {

    private var mutableListReservationState: MutableLiveData<Event<List<LotReservation>>> = MutableLiveData()
    val listReservationState: LiveData<Event<List<LotReservation>>>
        get() {
            return mutableListReservationState
        }
}
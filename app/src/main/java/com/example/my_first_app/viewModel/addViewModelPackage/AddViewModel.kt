package com.example.my_first_app.viewModel.addViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.ParkingLot
import com.example.domain.usecases.AddReservationUseCase
import com.example.domain.usecases.GetLotListUseCase
import com.example.my_first_app.utils.Event

class AddViewModel(val getLotList: AddReservationUseCase) : ViewModel() {

    private var mutableAddReservationState: MutableLiveData<Event<List<ParkingLot>>> = MutableLiveData()
    val listParkingLotState: LiveData<Event<List<ParkingLot>>>
        get() {
            return mutableListParkingLotState
        }
}
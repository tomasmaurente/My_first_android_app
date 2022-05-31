package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entities.Lot
import com.example.domain.usecases.GetLotListUseCase
import com.example.my_first_app.utils.Event

class LotViewModel (val getLotList: GetLotListUseCase) : ViewModel() {

    private var mutableListParkingLotState: MutableLiveData<Event<List<Lot>>> = MutableLiveData()

    val listParkingLotState: LiveData<Event<List<Lot>>>
        get() {
            return mutableListParkingLotState
        }

}
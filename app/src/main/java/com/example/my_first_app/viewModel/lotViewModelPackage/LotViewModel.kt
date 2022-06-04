package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Lot
import com.example.domain.usecases.GetLotListUseCase
import com.example.my_first_app.utils.Event
import kotlinx.coroutines.launch

class LotViewModel (private val getLotList: GetLotListUseCase) : ViewModel() {

    private var mutableListParkingLotState: MutableLiveData<List<Lot>> = MutableLiveData()

    val listParkingLotState: LiveData<List<Lot>>
        get() {
            return mutableListParkingLotState
        }
    fun getLots(parkingId: String) = viewModelScope.launch {
        val lotResponse = getLotList.invoke()
        mutableListParkingLotState.postValue(lotResponse)
    }

    fun getNumberOfFreeLots(lotList: List<Lot>): Int{
        var parkingAvailability = lotList.size
        lotList.forEach(){
            if (it.reservations.isEmpty()){
                parkingAvailability --
            }
        }
        return parkingAvailability
    }

}
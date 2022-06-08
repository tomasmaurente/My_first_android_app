package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.GetLotListRepositoryImp
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.entities.Lot
import com.example.domain.entities.ParkingLotModel
import com.example.domain.entities.Result
import com.example.domain.usecases.GetLotListUseCase
import com.example.my_first_app.utils.Event
import kotlinx.coroutines.launch

class LotViewModel (private val getLotList: GetLotListUseCase) : ViewModel() {

    private var mutableListParkingLotState: MutableLiveData<List<ParkingLotModel>> = MutableLiveData()

    val listParkingLotState: LiveData<List<ParkingLotModel>>
        get() {
            return mutableListParkingLotState
        }
    
    fun getLots(parkingId: String) = viewModelScope.launch {
        /*val lotResponse = getLotList.invoke()
        mutableListParkingLotState.postValue(lotResponse)*/
        val getLots = GetLotListRepositoryImp()
        var lots = getLots.getLotList(parkingId)
        when (lots){
            is Result.Success -> { mutableListParkingLotState.postValue(lots.value.lotList)}
        }
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
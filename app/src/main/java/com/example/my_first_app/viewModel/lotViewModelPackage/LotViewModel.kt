package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.*
import com.example.domain.usecases.AddReservationUseCase
import com.example.domain.usecases.GetLotListUseCase
import com.example.domain.usecases.GetReservationListUseCase
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.util.*

class LotViewModel (private val getLotListUseCase: GetLotListUseCase,
                    private val getReservationListUseCase: GetReservationListUseCase,
                    private val addReservationUseCase: AddReservationUseCase) : ViewModel() {

    private var mutableParkingState: MutableLiveData<List<Lot>> = MutableLiveData()

    val parkingState: LiveData<List<Lot>>
        get() {
            return mutableParkingState
        }

    fun createParkingState(parkingId: String, localDataBase: Boolean = false) = viewModelScope.launch {
        var lotListModel = getLotList(parkingId,localDataBase)
        var reservationListModel = getReservationList(parkingId,localDataBase)

        var newLotList = createLotList(lotListModel,reservationListModel)
        var oldLotList = mutableParkingState.value

        if( newLotList != oldLotList){
            mutableParkingState.postValue(newLotList)
        }
    }

    private fun createLotList(lotListModel: List<ParkingLotModel>, reservationListModel: List<ReservationModel>): List<Lot>{
        var lotList = mutableListOf<Lot>()
        lotListModel.forEach { lot ->
            var reservationList = mutableListOf<Reservation>()
            reservationListModel.forEach{ reservation ->
                if ( lot.parkingLot == reservation.parkingLot){
                    reservationList.add(Reservation(reservation.id,reservation.startDate,reservation.endDate,reservation.authorizationCode,reservation.parkingLot))
                }
            }
            var actualLot = Lot(lot.parkingLot, reservationList)
            lotList.add(actualLot)
        }
        return lotList
    }

    private var mutableLotListFromDataBaseState: MutableLiveData<List<Lot>> = MutableLiveData()

    val lotListFromDataBase: LiveData<List<Lot>>
        get() {
            return mutableLotListFromDataBaseState
        }
    fun chargeDataBase(parkingId: String) = viewModelScope.launch {
        var lots = createLotList(getLotList(parkingId,false),getReservationList(parkingId,false))
        lots.forEach{ lot ->
            lot.reservations.forEach { reservation ->
                addReservationUseCase(parkingId,reservation,true)
            }
        }
        mutableLotListFromDataBaseState.postValue(lots)
    }

    private suspend fun getLotList(parkingId: String, localDataBase: Boolean): List<ParkingLotModel>  {
        val getLots = getLotListUseCase(parkingId, localDataBase)
        when (getLots){
            is Result.Success -> return getLots.value.lotList
            else -> return listOf<ParkingLotModel>()
        }
    }

    private suspend fun getReservationList(parkingId: String, localDataBase: Boolean): List<ReservationModel>{
        val getReservations = getReservationListUseCase(parkingId, localDataBase)
        when(getReservations){
            is Result.Success -> return getReservations.value.reservationList
            else -> return listOf<ReservationModel>()
        }
    }

    fun getNumberOfFreeLots(lotList: List<Lot>): Int{
        val dateTime = Calendar.getInstance()
        var parkingAvailability = lotList.size
        lotList.forEach(){ lot ->
            lot.isFree = true
            lot.reservations.forEach() { reservation ->
                if (reservation.startDateTimeInMillis < dateTime.timeInMillis
                    && reservation.endDateTimeInMillis > dateTime.timeInMillis) {
                    lot.isFree = false
                    parkingAvailability--
                }
            }
        }
        return parkingAvailability
    }
}
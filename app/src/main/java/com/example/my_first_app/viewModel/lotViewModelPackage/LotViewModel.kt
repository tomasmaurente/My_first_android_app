package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.*
import com.example.domain.usecases.AddReservationUseCase
import com.example.domain.usecases.GetLotListUseCase
import com.example.domain.usecases.GetReservationListUseCase
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

    fun chargeParkingStateFromDataBase(parkingId: String) = viewModelScope.launch {
        var lots = createLotList(getLotList(parkingId,true),getReservationList(parkingId,true))
        lots.forEach{ lot ->
            lot.reservations.forEach { reservation ->
                addReservationUseCase(parkingId,reservation,true)
            }
        }
        mutableParkingState.postValue(lots)
    }

    fun updateParkingStateFromService(parkingId: String) = viewModelScope.launch {
        val parkingStateValue: List<Lot>? = parkingState.value
        var parkingStateAux = createLotList(getLotList(parkingId,false),getReservationList(parkingId,false))

        var lotIndex = 0
        var reservationIndex = 0
        if(parkingStateAux.size == parkingStateValue?.size ){
            parkingStateAux.forEach{ lot ->
                lotIndex ++
                if(parkingStateAux[lotIndex].reservations.size == parkingStateValue?.get(lotIndex)?.reservations.size){
                    while (reservationIndex < parkingStateAux[lotIndex].reservations.size){
                        reservationIndex ++
                        if (parkingStateAux[lotIndex].reservations[reservationIndex].id != parkingStateValue?.get(lotIndex)?.reservations?.get(reservationIndex)?.id){
                            mutableParkingState.postValue(parkingStateAux)
                        }
                    }
                } else {
                    mutableParkingState.postValue(parkingStateAux)
                }
            }
        } else {
            mutableParkingState.postValue(parkingStateAux)
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
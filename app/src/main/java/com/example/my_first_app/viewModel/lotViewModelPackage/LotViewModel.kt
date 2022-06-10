package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.entities.*
import com.example.domain.usecases.GetLotListUseCase
import com.example.domain.usecases.GetReservationListUseCase
import com.example.my_first_app.viewModel.reservationsViewModelPackage.ReservationViewModel
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis
import java.util.*

class LotViewModel (private val getLotListUseCase: GetLotListUseCase,
                    private val getReservationListUseCase: GetReservationListUseCase) : ViewModel() {

    private var mutableParkingState: MutableLiveData<List<Lot>> = MutableLiveData()

    val parkingState: LiveData<List<Lot>>
        get() {
            return mutableParkingState
        }

    fun createParkingState(parkingId: String, localDataBase: Boolean = false) = viewModelScope.launch {
        var lotListModel = getLotList(parkingId,localDataBase)
        var reservationListModel = getReservationList(parkingId,localDataBase)

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
        mutableParkingState.postValue(lotList)
    }

    private suspend fun getLotList(parkingId: String, localDataBase: Boolean): List<ParkingLotModel>  {
        val getLots = getLotListUseCase(parkingId, localDataBase)
        when (getLots){
            is Result.Success -> return getLots.value.lotList
            else -> return listOf<ParkingLotModel>()
        }
    }

    private suspend fun getReservationList(parkingId: String, localDataBase: Boolean): List<ReservationModel>{
        val getReservations = getReservationListUseCase(parkingId)
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
package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.*
import com.example.domain.usecases.LotUseCase
import com.example.domain.usecases.ReservationUseCase
import kotlinx.coroutines.launch

class LotViewModel (private val getLotListUseCase: LotUseCase,
                    private val getReservationListUseCase: ReservationUseCase) : ViewModel() {

    private var mutableParkingState: MutableLiveData<List<Lot>> = MutableLiveData()

    val parkingState: LiveData<List<Lot>>
        get() {
            return mutableParkingState
        }

    fun createParkingState(localDataBase: Boolean) = viewModelScope.launch {
        var res = getReservationList(localDataBase)
        var lotList = createLotList(getLotList(localDataBase),res)
        mutableParkingState.postValue(lotList)
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

    private suspend fun getLotList(localDataBase: Boolean): List<ParkingLotModel>  {
        return when (val getLots = getLotListUseCase(localDataBase)){
            is Result.Success -> getLots.value?.lotList ?: listOf<ParkingLotModel>()
            else -> listOf<ParkingLotModel>()
        }
    }

    private suspend fun getReservationList(localDataBase: Boolean): List<ReservationModel>{
        return when(val getReservations = getReservationListUseCase(localDataBase)){
            is Result.Success -> getReservations.value?.reservationList ?: listOf<ReservationModel>()
            else -> listOf<ReservationModel>()
        }
    }

    fun getNumberOfFreeLots(lotList: List<Lot>): Int{
        val dateTime = System.currentTimeMillis()
        var parkingAvailability = lotList.size
        var currentReservation = -1
        lotList.forEach(){ lot ->
            currentReservation = -1
            lot.freeAt = -1
            lot.reservations.forEach() { reservation ->
                currentReservation ++
                if (reservation.startDateTimeInMillis < dateTime
                    && reservation.endDateTimeInMillis > dateTime
                    && lot.freeAt == -1) {
                    lot.freeAt = currentReservation
                    parkingAvailability--
                }
            }
        }
        return parkingAvailability
    }
}
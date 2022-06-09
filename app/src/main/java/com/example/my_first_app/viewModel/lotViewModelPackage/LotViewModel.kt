package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.GetLotListRepositoryImp
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.data.repositories.GetReservationListRepositoryImp.getReservationList
import com.example.data.service.ParkingService
import com.example.domain.entities.*
import com.example.domain.usecases.GetLotListUseCase
import com.example.domain.usecases.GetReservationListUseCase
import com.example.my_first_app.utils.Event
import kotlinx.coroutines.launch
import java.util.*

class LotViewModel (private val getLotList: GetLotListUseCase) : ViewModel() {

    private var mutableParkingState: MutableLiveData<List<Lot>> = MutableLiveData()

    val parkingState: LiveData<List<Lot>>
        get() {
            return mutableParkingState
        }

    /*
    this method ask to the server the existing lots, and reservations. Once all the info is set,
    the method creates a list of Lots which is saved in mutableParkingState.

    The problem begins in the LotFragment where we can´t see the new list.

     */
    fun createParkingState(parkingId: String) = viewModelScope.launch {
        var lotListModel = getLotList(parkingId)
        var reservationListModel = getReservationList(parkingId)

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

    private suspend fun getLotList(parkingId: String): List<ParkingLotModel>  {
        val getLots = getLotList.invoke(parkingId) // hay q llamar al caso de uso y el caso de uso llama al repo, aca estoy llamando directamente al repo
        when (getLots){
            is Result.Success -> return getLots.value.lotList
            else -> return listOf<ParkingLotModel>()
        }
    }

    private suspend fun getReservationList(parkingId: String): List<ReservationModel>{
        val getReservations = GetReservationListRepositoryImp
        var reservations = getReservations.getReservationList(parkingId)
        when(reservations){
            is Result.Success -> return reservations.value.reservationList
            else -> return listOf<ReservationModel>()
        }
    }

    fun getNumberOfFreeLots(lotList: List<Lot>): Int{
        val dateTime = Calendar.getInstance()
        var parkingAvailability = lotList.size
        lotList.forEach(){ lot ->
            lot.reservations.forEach() { reservation ->
                if (reservation.startDateTimeInMillis < dateTime.timeInMillis
                    && reservation.endDateTimeInMillis > dateTime.timeInMillis) {
                    parkingAvailability--
                }
            }
        }
        return parkingAvailability
    }

}
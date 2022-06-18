package com.example.my_first_app.viewModel.addViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local_data_base.ParkingDataBase
import com.example.data.utils.ParkingMapper
import com.example.domain.utils.AddPossibilities
import com.example.domain.entities.Reservation
import com.example.domain.usecases.AddUseCase
import kotlinx.coroutines.launch

class AddViewModel(private val addReservationUseCase: AddUseCase,
                   private val parkingDataBase: ParkingDataBase) : ViewModel() {

    private val parkingId: String = "-N0TUDrXZUxA_wbd391E"

    private var mutableAddReservationState: MutableLiveData<AddPossibilities> = MutableLiveData()

    val addReservationState: LiveData<AddPossibilities>
        get() {
            return mutableAddReservationState
        }

    fun setWaitingState(){
        mutableAddReservationState.postValue(AddPossibilities.Waiting)
    }

    fun addReservation(reservation: Reservation) = viewModelScope.launch {
        val reservationState: AddPossibilities
        val reservationListOfLot =
            parkingDataBase.reservationDataBaseDao().findReservationList(reservation.parkingLot)

        reservationState = addReservationUseCase(
            parkingId,
            reservation,
            ParkingMapper.reservationRoomListToReservationListModel(reservationListOfLot)
        )
        mutableAddReservationState.postValue(reservationState)
    }
}
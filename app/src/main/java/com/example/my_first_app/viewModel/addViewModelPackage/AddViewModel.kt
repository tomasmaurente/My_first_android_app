package com.example.my_first_app.viewModel.addViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local_data_base.ParkingDataBase
import com.example.data.utils.ParkingMapper
import com.example.domain.entities.Reservation
import com.example.domain.usecases.AddUseCase
import com.example.domain.utils.AddPossibilities
import kotlinx.coroutines.launch

// FIXME: Rename to AddReservationViewModel
class AddViewModel(private val addReservationUseCase: AddUseCase,
                   // FIXME: ViewModel shouldn't access data base directly
                   private val parkingDataBase: ParkingDataBase) : ViewModel() {

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
        // FIXME: this code belongs to the data source, and should be accessed through the use case
        //  and the reservation repository
        val reservationListOfLot =
            parkingDataBase.reservationDataBaseDao().findReservationList(reservation.parkingLot)

        reservationState = addReservationUseCase(
            reservation,
            ParkingMapper.reservationRoomListToReservationListModel(reservationListOfLot)
        )
        mutableAddReservationState.postValue(reservationState)

    }
}
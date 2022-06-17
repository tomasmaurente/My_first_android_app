package com.example.my_first_app.viewModel.addViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.AddPossibilities
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.usecases.AddUseCase
import com.example.domain.usecases.ReservationUseCase
import kotlinx.coroutines.launch

class AddViewModel(private val addReservationUseCase: AddUseCase,
                   private val reservationUseCase: ReservationUseCase) : ViewModel() {

    private val parkingId: String = "-N0TUDrXZUxA_wbd391E"

    private var mutableAddReservationState: MutableLiveData<AddPossibilities> = MutableLiveData()

    val addReservationState: LiveData<AddPossibilities>
        get() {
            return mutableAddReservationState
        }

    fun addReservation(reservation: Reservation) = viewModelScope.launch{
        if(reservation.parkingLot > -1){

            val reservationState = AddPossibilities.Successful
            val reservationListOfLot = reservationUseCase(reservation.parkingLot)
            when(reservationListOfLot){
                is Result.Success -> {
                    val reservationList = reservationListOfLot.value?.reservationList
                    reservationList?.forEach { reservationFromDataBase ->
                        if (   reservationFromDataBase.endDate > reservation.startDateTimeInMillis
                            || reservationFromDataBase.startDate > reservation.endDateTimeInMillis){
                            mutableAddReservationState.postValue(AddPossibilities.Occupied)
                            return@forEach
                        }
                    }
                }
                is Result.Failure -> mutableAddReservationState.postValue(AddPossibilities.Successful)
            }
            if (reservationState == AddPossibilities.Successful) {
                var newAddition = addReservationUseCase(parkingId, reservation, false)
                when (newAddition) {
                    is Result.Success -> mutableAddReservationState.postValue(AddPossibilities.Successful)
                    is Result.Failure -> mutableAddReservationState.postValue(AddPossibilities.Fail)
                }
            }
        } else {
            mutableAddReservationState.postValue(AddPossibilities.IncorrectParameters)
        }
    }
}
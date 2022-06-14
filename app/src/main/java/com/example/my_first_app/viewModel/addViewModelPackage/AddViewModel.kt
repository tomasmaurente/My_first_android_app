package com.example.my_first_app.viewModel.addViewModelPackage

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.entities.Lot
import com.example.domain.entities.ParkingLotModel
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.usecases.AddReservationUseCase
import kotlinx.coroutines.launch

class AddViewModel(private val addReservationUseCase: AddReservationUseCase) : ViewModel() {

    private val parkingId: String = "-N0TUDrXZUxA_wbd391E"

    private var mutableAddReservationState: MutableLiveData<Boolean> = MutableLiveData()

    val addReservationState: LiveData<Boolean>
        get() {
            return mutableAddReservationState
        }

    fun addReservation(reservation: Reservation) = viewModelScope.launch{
        if( reservation.startDateTimeInMillis != null
            && reservation.endDateTimeInMillis != null
            && reservation.parkingLot != 0 &&
               reservation.authorizationCode != null){

            var newAddition = addReservationUseCase(parkingId,reservation,false)

            when(newAddition){
                is Result.Success -> mutableAddReservationState.postValue(true)
                is Result.Failure -> mutableAddReservationState.postValue(false)
            }
        } else {
            mutableAddReservationState.postValue(false)
        }
    }
}
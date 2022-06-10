package com.example.my_first_app.viewModel.reservationsViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Lot
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.usecases.DeleteResevationUseCase
import kotlinx.coroutines.launch

class ReservationViewModel (private val deleteReservation : DeleteResevationUseCase) : ViewModel() {

    private var mutableDeleteState: MutableLiveData<Boolean> = MutableLiveData()

    val deletedSuccessfully: LiveData<Boolean>
    get() {
        return mutableDeleteState
    }

    fun deleteReservation(parkingId: String, authorizationCode: String, reservation: Reservation) = viewModelScope.launch {

        if(reservation.authorizationCode == authorizationCode){
            val deleteReservation = deleteReservation.invoke(parkingId,reservation,authorizationCode)
            when(deleteReservation){
                is Result.Success -> {mutableDeleteState.value = true}
                is Result.Failure -> {mutableDeleteState.value = false}
            }
        } else {
            mutableDeleteState.value = false
        }
    }

    fun updatedeletedSuccessfullyVariable(){
        mutableDeleteState.value = false
    }
}
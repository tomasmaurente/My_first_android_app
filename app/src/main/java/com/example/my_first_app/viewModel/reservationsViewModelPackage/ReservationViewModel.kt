package com.example.my_first_app.viewModel.reservationsViewModelPackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.usecases.DeleteReservationUseCase
import kotlinx.coroutines.launch

class ReservationViewModel (private val deleteReservationUseCase : DeleteReservationUseCase) : ViewModel() {

    private var mutableDeleteState: MutableLiveData<Boolean> = MutableLiveData()

    val deletedSuccessfully: LiveData<Boolean>
    get() {
        return mutableDeleteState
    }

    fun deleteReservation(parkingId: String, authorizationCode: String, reservation: Reservation) = viewModelScope.launch { // Delete reservation in DataBase
        val deleteReservation = deleteReservationUseCase(parkingId,reservation,authorizationCode) // Delete reservation in Service
        when(deleteReservation){
            is Result.Success -> {mutableDeleteState.value = true}
            is Result.Failure -> {mutableDeleteState.value = false}
        }
    }
}
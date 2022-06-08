package com.example.my_first_app.viewModel.addViewModelPackage

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.entities.Reservation
import com.example.domain.usecases.AddReservationUseCase

class AddViewModel(val getLotList: AddReservationUseCase) : ViewModel() {

    fun addReservation(reservation: Reservation): Boolean{
        if( reservation.startDateTimeInMillis != null && reservation.endDateTimeInMillis != null && reservation.parkingLot != 0 && reservation.authorizationCode != null){
            val getReservationListRepositoryImp = GetReservationListRepositoryImp
            getReservationListRepositoryImp.addReservation(reservation) 
            return true
        } else {
            return false
        }
    }
}
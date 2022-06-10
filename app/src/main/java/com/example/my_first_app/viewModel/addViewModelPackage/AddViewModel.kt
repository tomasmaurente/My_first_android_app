package com.example.my_first_app.viewModel.addViewModelPackage

import androidx.lifecycle.ViewModel
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.entities.ParkingLotModel
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.usecases.AddReservationUseCase

class AddViewModel(val addReservation: AddReservationUseCase) : ViewModel() {

    fun addReservation(reservation: Reservation): Boolean{
        if( reservation.startDateTimeInMillis != null
            && reservation.endDateTimeInMillis != null
            && reservation.parkingLot != 0 &&
               reservation.authorizationCode != null){

            addReservation.invoke(reservation.parkingLot,reservation.startDateTimeInMillis,reservation.endDateTimeInMillis,reservation.authorizationCode)

            val getReservationListRepositoryImp = GetReservationListRepositoryImp
            getReservationListRepositoryImp.addReservation(reservation) 
            return true

        } else {
            return false
        }
    }
}
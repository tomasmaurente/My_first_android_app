package com.example.data.repositories

import com.example.domain.entities.Reservation
import com.example.domain.repositories.DeleteReservationRepository

class DeleteReservationRepositoryImp: DeleteReservationRepository {

    override fun deleteReservation(reservation: Reservation, authorizationCode: String): Boolean {
        if(reservation.authorizationCode == authorizationCode){
            val getReservationListRepository = GetReservationListRepositoryImp
            getReservationListRepository.deleteReservation(reservation)
            return false
        } else {
            return false
        }
    }
}
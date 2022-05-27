package com.example.data.repositories

import com.example.domain.entities.Reservation
import com.example.domain.repositories.DeleteReservationRepository

class DeleteReservationRepositoryImp: DeleteReservationRepository {

    override fun deleteReservation(reservation: Reservation, authorizationCode: String): Boolean {
        return true
    }
}
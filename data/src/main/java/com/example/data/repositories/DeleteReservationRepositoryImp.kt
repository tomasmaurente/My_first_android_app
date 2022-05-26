package com.example.data.repositories

import com.example.domain.entities.LotReservation
import com.example.domain.repositories.DeleteReservationRepository

class DeleteReservationRepositoryImp: DeleteReservationRepository {
    override fun deleteReservation(reservation: LotReservation, authorizationCode: Int): Boolean {
        return true
    }
}
package com.example.domain.usecases

import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteReservationRepository

class DeleteResevationUseCase{
        lateinit var deleteReservationRepository: DeleteReservationRepository
        suspend operator fun invoke(parkingId: String,
                                    reservation: Reservation,
                                    authorizationCode: String ): Result<Boolean>{
             return deleteReservationRepository.deleteReservation(parkingId, reservation,authorizationCode)
        }
}
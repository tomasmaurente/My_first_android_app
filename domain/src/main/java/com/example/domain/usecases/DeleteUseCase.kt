package com.example.domain.usecases

import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteRepository

class DeleteReservationUseCase{
        lateinit var deleteReservationRepository: DeleteRepository
        suspend operator fun invoke(parkingId: String,
                                    reservation: Reservation,
                                    localDataBase: Boolean
                                                        ): Result<Boolean>{
             return deleteReservationRepository.deleteReservation(parkingId, reservation, localDataBase)
        }
}
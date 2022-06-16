package com.example.data.repositories

import com.example.data.local_data_base.ReservationDataBase
import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteRepository

class DeleteRepositoryImp(
    private val deleteService : ParkingService,
    private val deleteDataBase: ReservationDataBase
                                        ): DeleteRepository {

    override suspend fun deleteReservation(parkingId: String, reservation: Reservation, localDataBase: Boolean
    ): Result<Boolean> {
        if (localDataBase) {
            deleteFromDataBase(reservation.id)
            return Result.Success(true)
        } else {
            return deleteFromService(parkingId, reservation)
        }
    }

    private suspend fun deleteFromDataBase(reservationId: String){
        var delete = deleteDataBase.reservationDataBaseDao().deleteReservation(reservationId)
        return delete
    }

    private suspend fun deleteFromService(parkingId: String, reservation: Reservation
    ): Result<Boolean> {
        var result = deleteService.deleteReservation(parkingId, reservation.id)
        return when (result) {
            is Result.Success -> {
                Result.Success(result.value as Boolean)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }



}


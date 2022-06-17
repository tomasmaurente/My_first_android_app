package com.example.data.repositories

import com.example.data.local_data_base.ParkingDataBase
import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteRepository

class DeleteRepositoryImp(
    private val deleteService : ParkingService,
    private val parkingDataBase: ParkingDataBase
                                        ): DeleteRepository {

    override suspend fun deleteReservation(parkingId: String, reservation: Reservation
    ): Result<Boolean> {
        deleteFromDataBase(reservation.id)
        return deleteFromService(parkingId, reservation)
    }

    private suspend fun deleteFromDataBase(reservationId: String) {
        return parkingDataBase.reservationDataBaseDao().deleteReservation(reservationId)
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


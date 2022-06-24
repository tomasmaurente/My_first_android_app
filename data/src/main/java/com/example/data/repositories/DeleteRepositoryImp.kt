package com.example.data.repositories

import com.example.data.local_data_base.ParkingDataBase
import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteRepository

// FIXME: Merge with Reservation repository
class DeleteRepositoryImp(
    private val deleteService : ParkingService,
    // FIXME: Repository should access the database through a data source class
    private val parkingDataBase: ParkingDataBase
                                        ): DeleteRepository {

    override suspend fun deleteReservation(reservation: Reservation
    ): Result<Boolean> {
        // FIXME: If deletion from the database failed, error should be returned
        deleteFromDataBase(reservation.id)
        return deleteFromService(reservation)
    }

    // FIXME: This code belongs to the data source.
    private suspend fun deleteFromDataBase(reservationId: String) {
        return parkingDataBase.reservationDataBaseDao().deleteReservation(reservationId)
    }

    private suspend fun deleteFromService(reservation: Reservation
    ): Result<Boolean> {
        var result = deleteService.deleteReservation(reservation.id)
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


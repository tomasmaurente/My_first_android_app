package com.example.data.repositories

import com.example.data.local_data_base.ParkingDataBase
import com.example.data.local_data_base.entities.ReservationRoom
import com.example.data.responseObjects.ReservationRequest
import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.AddRepository

// FIXME: Merge with Reservation repository
class AddRepositoryImp(
    private val parkingService: ParkingService,
    // FIXME: Repository should access the database through a data source class
    private val parkingDataBase: ParkingDataBase) : AddRepository {

    override suspend fun addReservation(
        reservation: Reservation
    ): Result<Boolean> {
        // Add reservation to data base
        // FIXME: If addition to the database fails, error should be returned
        addReservationToDataBase(reservation)
        // Add reservation to Service
        return when (addReservationToService(reservation)){
            is Result.Success -> Result.Success(true)
            else -> Result.Failure(null)
        }
    }


    private suspend fun addReservationToService(reservation: Reservation): Result<Boolean> {
        // FIXME: Mapper should be used for this transformation
        val newReservation = ReservationRequest(
            reservation.authorizationCode,
            reservation.startDateTimeInMillis.toString(),
            reservation.endDateTimeInMillis.toString(),
            reservation.parkingLot
        )

        var result = parkingService.addReservation(newReservation)
        return when (result) {
            is Result.Success -> {
                Result.Success(result.value as Boolean)
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }

    // FIXME: This code belongs to the data source, transformation should be done by a mapper.
    private suspend fun addReservationToDataBase(
        reservation: Reservation
    ){
        parkingDataBase.reservationDataBaseDao().insertNewReservation(ReservationRoom(reservation.id,
                                                                                        reservation.authorizationCode,
                                                                                        reservation.startDateTimeInMillis,
                                                                                        reservation.endDateTimeInMillis,
                                                                                        reservation.parkingLot))
    }
}

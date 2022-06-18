package com.example.data.repositories

import com.example.data.local_data_base.ParkingDataBase
import com.example.data.local_data_base.entities.LotRoom
import com.example.data.local_data_base.entities.ReservationRoom
import com.example.data.responseObjects.ReservationRequest
import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.AddRepository

class AddRepositoryImp(
    private val parkingService: ParkingService,
    private val parkingDataBase: ParkingDataBase)   : AddRepository {

    override suspend fun addReservation(
        parkingId: String,
        reservation: Reservation
    ): Result<Boolean> {
        // Add reservation to data base
        addReservationToDataBase(reservation)
        // Add reservation to Service
        return when (addReservationToService(reservation)){
            is Result.Success -> Result.Success(true)
            else -> Result.Failure(null)
        }
    }


    private suspend fun addReservationToService(reservation: Reservation): Result<Boolean> {
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

    private suspend fun addLotToDataBase(
        parkingId: Int
    ){
        parkingDataBase.lotDataBaseDao().insertNewLot(LotRoom(parkingId))
    }

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

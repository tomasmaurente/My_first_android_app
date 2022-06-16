package com.example.data.repositories

import com.example.data.local_data_base.LotDataBase
import com.example.data.local_data_base.ReservationDataBase
import com.example.data.local_data_base.entities.LotRoom
import com.example.data.local_data_base.entities.ReservationRoom
import com.example.data.responseObjects.ReservationRequest
import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Result
import com.example.domain.repositories.AddRepository

class AddRepositoryImp(
    private val parkingService: ParkingService,
    private val lotDataBase: LotDataBase,
    private val reservationDataBase: ReservationDataBase)   : AddRepository {

    override suspend fun addLot(lot: Int): Result<Boolean> {
        addLotToDataBase(lot)
        return Result.Success(true )
    }

    override suspend fun addReservation(reservation: Reservation): Result<Boolean> {
        addReservationToDataBase(reservation)
        return Result.Success(true )
    }

    override suspend fun addReservation(
        parkingId: String,
        reservation: Reservation,
        localDataBase: Boolean
    ): Result<Boolean> {
        if(localDataBase){
            addReservationToDataBase(reservation)
            return Result.Success(true )
        } else {
            return when (addReservationToService(parkingId,reservation)){
                is Result.Success -> Result.Success(true)
                else -> Result.Failure(null)
            }
        }
    }

    private suspend fun addReservationToService(
        parkingId: String,
        reservation: Reservation,
    ): Result<Boolean> {
        val newReservation = ReservationRequest(
            reservation.authorizationCode,
            reservation.startDateTimeInMillis.toString(),
            reservation.endDateTimeInMillis.toString(),
            reservation.parkingLot
        )

        var result = parkingService.addReservation(parkingId, newReservation)
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
        lotDataBase.lotDataBaseDao().insertNewLot(LotRoom(parkingId))
    }

    private suspend fun addReservationToDataBase(
        reservation: Reservation
    ){
        reservationDataBase.reservationDataBaseDao().insertNewReservation(ReservationRoom(reservation.id,
                                                                                        reservation.authorizationCode,
                                                                                        reservation.startDateTimeInMillis,
                                                                                        reservation.endDateTimeInMillis,
                                                                                        reservation.parkingLot))
    }
}

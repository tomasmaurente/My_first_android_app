package com.example.data.repositories

import com.example.data.mappers.ParkingMapper
import com.example.data.local_data_base.ReservationDataBase
import com.example.data.service.ParkingService
import com.example.domain.entities.*
import com.example.domain.repositories.GetReservationListRepository

class GetReservationListRepositoryImp(
    private val reservationService : ParkingService,
    private val reservationDataBase: ReservationDataBase
                                        ): GetReservationListRepository {


    override suspend fun getReservationList(parkingId: String, localDataBase: Boolean): Result<ReservationListModel> {
        if (localDataBase){
            return getLocalReservationList()
        } else {
            return getServiceReservationList(parkingId)
        }
    }

    private suspend fun getServiceReservationList(parkingId: String): Result<ReservationListModel>{
        val result =  reservationService.getReservationList(parkingId)
        return when (result){
            is Result.Success -> {
                Result.Success(ParkingMapper.toReservationListResponseToModel(result.value!!))
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }

    private suspend fun getLocalReservationList(): Result<ReservationListModel>{
        val reservationList = reservationDataBase.characterDao().findReservationList()
        return Result.Success(ParkingMapper.reservationRoomListToReservationListModel(reservationList))
    }
}

/*
private var reservations = mutableListOf<Reservation>(
        Reservation("ingreseId",1539525000,1539525000, "1",1),
        Reservation("ingreseId",5553125099,5553125099, "1",2),
        Reservation("ingreseId",9153925055,9153925055, "1",3),
        Reservation("ingreseId",1539525000,1539525000, "1",1),
    )

    fun addReservation(reservation : Reservation){
        reservations.add(reservation)
    }

    fun deleteReservation(reservation: Reservation){
        reservations.remove(reservation)
    }

    fun getReservationList(): List<Reservation> {
        return reservations
    }
 */
package com.example.data.repositories

import com.example.data.utils.ParkingMapper
import com.example.data.local_data_base.ReservationDataBase
import com.example.data.service.ParkingService
import com.example.domain.entities.*
import com.example.domain.repositories.ReservationRepository
import com.example.domain.usecases.AddUseCase

class ReservationRepositoryImp(
    private val reservationService : ParkingService,
    private val reservationDataBase: ReservationDataBase,
    private val addReservationUseCase: AddUseCase
                                        ): ReservationRepository {


    override suspend fun getReservationList(parkingId: String, localDataBase: Boolean): Result<ReservationListModel> {
        return when(localDataBase) {
            true -> {
                getLocalReservationList()
            }
            else -> {
                val reservationListFromService = getServiceReservationList(parkingId)
                updateDataBase(reservationListFromService)
                reservationListFromService
            }
        }
    }

    override suspend fun getReservationList(parkingId: Int): Result<ReservationListModel> {
        val reservationList = reservationDataBase.reservationDataBaseDao().findReservationList(parkingId)
        return if (reservationList != null) {
            Result.Success(ParkingMapper.reservationRoomListToReservationListModel(reservationList))
        } else {
            Result.Failure(null)
        }
    }

    private suspend fun updateDataBase(reservationList: Result<ReservationListModel>){
        when(reservationList){
            is Result.Success -> {
                val reservationListFromService = reservationList.value?.reservationList
                reservationListFromService?.forEach { reservation ->
                    addReservationUseCase(ParkingMapper.reservationModelToReservation(reservation))
                }
            }
        }
    }

    private suspend fun getServiceReservationList(parkingId: String): Result<ReservationListModel>{
        val result =  reservationService.getReservationList(parkingId)
        return when (result){
            is Result.Success -> {
                Result.Success(ParkingMapper.toReservationListResponseToModel(result.value))
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }

    private suspend fun getLocalReservationList(): Result<ReservationListModel>{
        val reservationList = reservationDataBase.reservationDataBaseDao().findReservationList()
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
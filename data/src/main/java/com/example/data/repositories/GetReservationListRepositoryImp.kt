package com.example.data.repositories

import com.example.data.local_data_base.ParkingMapper
import com.example.data.service.ParkingService
import com.example.domain.entities.*
import com.example.domain.repositories.GetReservationListRepository

object GetReservationListRepositoryImp: GetReservationListRepository {

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

    private val reservationService : ParkingService = ParkingService()

    override suspend fun getReservationList(parkingId: String): Result<ReservationListModel> {
        val result =  reservationService.getReservations(parkingId)
        return when (result){
            is Result.Success -> {
                Result.Success(ParkingMapper.toReservationListResponseToModel(result.value!!))
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }
}
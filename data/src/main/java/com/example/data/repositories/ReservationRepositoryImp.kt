package com.example.data.repositories

import com.example.data.local_data_base.ParkingDataBase
import com.example.data.utils.ParkingMapper
import com.example.data.service.ParkingService
import com.example.domain.entities.*
import com.example.domain.repositories.ReservationRepository

// FIXME: Rename to ReservationRepositoryImpl
class ReservationRepositoryImp(
    private val parkingService : ParkingService,
    // FIXME: Repository should access the database through a data source class
    private val parkingDataBase: ParkingDataBase
                                        ): ReservationRepository {


    override suspend fun getReservationList(localDataBase: Boolean): Result<ReservationListModel> {
        // FIXME: Use IF instead of WHEN
        return when(localDataBase) {
            true -> {
                getLocalReservationList()
            }
            else -> {
                val reservationListFromService = getServiceReservationList()
                updateDataBase(reservationListFromService)
                reservationListFromService
            }
        }
    }

    private suspend fun updateDataBase(reservationList: Result<ReservationListModel>){
        // FIXME: Use IF instead of WHEN
        when(reservationList){
            is Result.Success -> {

                // Check if any reservation is missing
                val reservationListFromService = reservationList.value?.reservationList
                reservationListFromService?.forEach { reservation ->
                    parkingDataBase.reservationDataBaseDao().insertNewReservation(
                        ParkingMapper.reservationModelToReservationRoom(reservation)
                    )
                }
                // Check if any spare reservation
                // FIXME: This can be simplified to:
                //  dbList.filter { it.id !in serviceList.map { item -> item.id } }
                //  .forEach { /* DELETE RESERVATION */ }
                var reservationDoNotExistInService = true
                val reservationListFromDataBase = parkingDataBase.reservationDataBaseDao().findReservationList()
                reservationListFromDataBase.forEach { reservationFromDataBase ->
                    reservationListFromService?.forEach { reservationFromService ->
                        if (reservationFromDataBase.id == reservationFromService.id){
                            reservationDoNotExistInService = false
                        }
                    }
                    if(reservationDoNotExistInService){
                        parkingDataBase.reservationDataBaseDao().deleteReservation(reservationFromDataBase.id)
                        reservationDoNotExistInService = true
                    }
                }
            }
        }
    }

    private suspend fun getServiceReservationList(): Result<ReservationListModel>{
        return when (val result =  parkingService.getReservationList()){
            is Result.Success -> {
                Result.Success(ParkingMapper.toReservationListResponseToModel(result.value))
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }

    private suspend fun getLocalReservationList(): Result<ReservationListModel>{
        val reservationList = parkingDataBase.reservationDataBaseDao().findReservationList()
        return Result.Success(ParkingMapper.reservationRoomListToReservationListModel(reservationList))
    }
}
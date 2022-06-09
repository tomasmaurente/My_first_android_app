package com.example.data.repositories

import com.example.data.local_data_base.ParkingMapper
import com.example.data.service.ParkingService
import com.example.domain.entities.Reservation
import com.example.domain.entities.Lot
import com.example.domain.entities.ParkingLotListModel
import com.example.domain.repositories.GetLotListRepository
import com.example.domain.entities.Result


class GetLotListRepositoryImp: GetLotListRepository{

    private var spots = listOf<Lot>(
        Lot(1, listOf(Reservation("hello",1653625063,1653625063,"hola",0))),
        Lot(2, listOf()),
        Lot(3, listOf(Reservation("hello",0,0,"",0))),
        Lot(4, listOf(Reservation("hello",0,0,"",0))),
        Lot(5, listOf(Reservation("hello",0,0,"",0))),
        Lot(6, listOf(Reservation("hello",0,0,"",0))),
        Lot(7, listOf()),
        Lot(8, listOf(Reservation("hello",0,0,"",0))),
        Lot(9, listOf(Reservation("hello",0,0,"",0))),
        Lot(10, listOf(Reservation("hello",0,0,"",0))),
        Lot(11, listOf(Reservation("hello",0,0,"",0))),
        Lot(12, listOf()),
        Lot(13, listOf(Reservation("hello",0,0,"",0))),
        Lot(14, listOf(Reservation("hello",0,0,"",0))),
        )

    fun getLotList(): List<Lot> {
        return spots
    }

    private val lotService : ParkingService = ParkingService()

    override suspend fun getLotList(parkingId: String): Result<ParkingLotListModel> {
        val result =  lotService.getLots(parkingId)
        return when (result){
            is Result.Success -> {
                Result.Success(ParkingMapper.toParkingListResponseToModel(result.value!!))
            }
            is Result.Failure -> {
                Result.Failure(result.exception)
            }
        }
    }

    //override suspend fun getLots(parkingId: String) = lotService.getLots(parkingId)

}
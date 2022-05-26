package com.example.data.repositories

import com.example.domain.entities.LotReservation
import com.example.domain.entities.ParkingLot
import com.example.domain.repositories.GetLotListRepository


class GetLotListRepositoryImp: GetLotListRepository {

    private var spots = listOf<ParkingLot>(
        ParkingLot(1,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(2,"","","", listOf(LotReservation("","","","","","",0))),
        ParkingLot(3,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(4,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(5,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(6,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(7,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(8,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(9,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(10,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(11,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(12,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(13,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(14,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(15,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(16,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(17,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(18,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(19,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(20,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(21,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(22,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(23,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(24,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(25,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(26,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(27,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(28,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(29,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(30,"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
    )

    override fun getLotList(): List<ParkingLot> {
        return spots
    }
}
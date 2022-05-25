package com.example.data.repositories

import com.example.domain.entities.LotReservation
import com.example.domain.entities.ParkingLot
import com.example.domain.repositories.GetListOfLotsRepository

class GetListOfLotsRepositoryImp: GetListOfLotsRepository {


    private var spots = listOf<ParkingLot>(
        ParkingLot(Integer(1),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(2),"","","", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(3),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(4),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(5),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(6),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(7),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(8),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(9),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(10),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(11),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(12),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(13),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(14),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(15),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(16),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(17),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(18),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(19),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(20),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(21),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(22),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(23),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(24),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(25),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(26),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(27),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(28),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(29),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
        ParkingLot(Integer(30),"lunes 21","mayo 2022","12:00 PM", listOf(LotReservation("","","","","","",0))),
    )

    override fun getListOfLots(): List<ParkingLot> {
        return spots
    }
}
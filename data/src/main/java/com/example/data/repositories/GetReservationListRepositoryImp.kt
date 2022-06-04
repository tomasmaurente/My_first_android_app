package com.example.data.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Lot
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

    override fun getReservationList(lot: Lot): List<Reservation> {
        return reservations
    }
}
package com.example.data.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Lot
import com.example.domain.repositories.GetReservationListRepository

class GetReservationListRepositoryImp: GetReservationListRepository {

    private var reservations = listOf<Reservation>(
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
        Reservation(100000,100000, "2022",1),
    )

    override fun getReservationList(lot: Lot): List<Reservation> {
        // return lot.reservations
        return reservations
    }
}
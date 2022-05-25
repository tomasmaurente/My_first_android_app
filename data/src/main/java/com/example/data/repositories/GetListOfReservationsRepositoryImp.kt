package com.example.data.repositories

import com.example.domain.entities.LotReservation
import com.example.domain.entities.ParkingLot
import com.example.domain.repositories.GetListOfReservationsRepository

class GetListOfReservationsRepositoryImp: GetListOfReservationsRepository {

    private var reservations = listOf<LotReservation>(
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
        LotReservation("12:00","Miercoles 1","Diciembre 2022","14:00","Jueves 2", "Diciembre 2022",555),
    )

    override fun getListOfReservations(lot: ParkingLot): List<LotReservation> {
        // return lot.reservations
        return reservations
    }
}
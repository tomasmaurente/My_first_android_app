package com.example.data.repositories

import com.example.domain.entities.Reservation
import com.example.domain.entities.Lot
import com.example.domain.repositories.GetLotListRepository


class GetLotListRepositoryImp: GetLotListRepository {

    private var spots = listOf<Lot>(
        Lot(1, listOf(Reservation(0,0,"",0))),
        Lot(2, listOf(Reservation(0,0,"",0))),
        Lot(3, listOf(Reservation(0,0,"",0))),
        Lot(4, listOf(Reservation(0,0,"",0))),
        Lot(5, listOf(Reservation(0,0,"",0))),
        Lot(6, listOf(Reservation(0,0,"",0))),
        Lot(7, listOf(Reservation(0,0,"",0))),
        Lot(8, listOf(Reservation(0,0,"",0))),
        Lot(9, listOf(Reservation(0,0,"",0))),
        Lot(10, listOf(Reservation(0,0,"",0))),
        Lot(11, listOf(Reservation(0,0,"",0))),
        Lot(12, listOf(Reservation(0,0,"",0))),
        Lot(13, listOf(Reservation(0,0,"",0))),
        Lot(14, listOf(Reservation(0,0,"",0))),
        Lot(15, listOf(Reservation(0,0,"",0))),
        Lot(16, listOf(Reservation(0,0,"",0))),
        Lot(17, listOf(Reservation(0,0,"",0))),
        Lot(18, listOf(Reservation(0,0,"",0))),
        Lot(19, listOf(Reservation(0,0,"",0))),
        Lot(20, listOf(Reservation(0,0,"",0))),
        Lot(21, listOf(Reservation(0,0,"",0))),
        Lot(22, listOf(Reservation(0,0,"",0))),
        Lot(23, listOf(Reservation(0,0,"",0))),
        Lot(24, listOf(Reservation(0,0,"",0))),
        Lot(25, listOf(Reservation(0,0,"",0))),
        Lot(26, listOf(Reservation(0,0,"",0))),
        Lot(27, listOf(Reservation(0,0,"",0))),
        Lot(28, listOf(Reservation(0,0,"",0))),
        Lot(29, listOf(Reservation(0,0,"",0))),
        Lot(30, listOf(Reservation(0,0,"",0))),
    )

    override fun getLotList(): List<Lot> {
        return spots
    }
}
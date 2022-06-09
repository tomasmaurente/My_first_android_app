package com.example.data.responseObjects

import com.example.domain.entities.Lot
import java.io.Serializable

data class LotResponse(var parkingLot: Int) : Serializable {
    fun toLotParkingLotModel(): Lot{
        return Lot(parkingLot, listOf())
    }
}

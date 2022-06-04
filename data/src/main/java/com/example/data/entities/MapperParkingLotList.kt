package com.example.data.entities

import com.example.data.responseObjects.ParkingLotListResponse
import com.example.data.responseObjects.ParkingLotResponse
import com.example.domain.entities.Parking
import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.ParkingLotModel

object MapperParkingLotList {
    fun toParkingListResponseToModel(listResponse: ParkingLotListResponse): ParkingLotListModel{
        var lotList = ArrayList<ParkingLotModel>()
        listResponse.lotList.forEach{
            lotList.add(toParkingLotResponseToModel(it))
        }
        return ParkingLotListModel(lotList, listResponse.owner,listResponse.parkingSize)
    }

    fun toParkingLotResponseToModel(lotResponse: ParkingLotResponse): ParkingLotModel{
        return ParkingLotModel(lotResponse.parkingLot)

    }
}
package com.example.data.local_data_base

import com.example.data.responseObjects.LotListResponse
import com.example.data.responseObjects.LotResponse
import com.example.data.responseObjects.ReservationListResponse
import com.example.data.responseObjects.ReservationResponse
import com.example.domain.entities.*

object ParkingMapper {

    fun toParkingListResponseToModel(listResponse: LotListResponse): ParkingLotListModel{
        var lotList = ArrayList<ParkingLotModel>()
        listResponse.lotList.forEach{
            lotList.add(toParkingLotResponseToModel(it))
        }
        return ParkingLotListModel(lotList, listResponse.owner,listResponse.parkingSize)
    }

    private fun toParkingLotResponseToModel(lotResponse: LotResponse): ParkingLotModel{
        return ParkingLotModel(lotResponse.parkingLot)
    }

    fun toReservationListResponseToModel(listResponse: ReservationListResponse): ReservationListModel {
        var reservationList = ArrayList<ReservationModel>()
        listResponse.reservationList.forEach{
            reservationList.add(toReservationResponseToModel(it))
        }
        return ReservationListModel(reservationList)
    }

    private fun toReservationResponseToModel(reservationResponse: ReservationResponse): ReservationModel{
        return ReservationModel(reservationResponse.id,
                                reservationResponse.authorizationCode,
                                reservationResponse.startDate.toLong(),
                                reservationResponse.endDate.toLong(),
                                reservationResponse.parkingLot
                                )
    }
}
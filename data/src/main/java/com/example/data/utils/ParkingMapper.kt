package com.example.data.utils

import com.example.data.local_data_base.entities.LotRoom
import com.example.data.local_data_base.entities.ReservationRoom
import com.example.data.responseObjects.LotListResponse
import com.example.data.responseObjects.LotResponse
import com.example.data.responseObjects.ReservationListResponse
import com.example.data.responseObjects.ReservationResponse
import com.example.domain.entities.*
import com.example.domain.entities.ParkingLotModel as ParkingLotModel

object ParkingMapper {

    fun toParkingListResponseToModel(listResponse: LotListResponse?): ParkingLotListModel{
        var lotList = ArrayList<ParkingLotModel>()
        listResponse?.lotList?.forEach{
            lotList.add(toParkingLotResponseToModel(it))
        }
        return ParkingLotListModel(lotList)
    }

    private fun toParkingLotResponseToModel(lotResponse: LotResponse): ParkingLotModel {
        return ParkingLotModel(lotResponse.parkingLot)
    }

    fun toReservationListResponseToModel(listResponse: ReservationListResponse?): ReservationListModel {
        var reservationList = ArrayList<ReservationModel>()
        listResponse?.reservationList?.forEach{
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

    fun lotRoomListToParkingLotListModel(lotRoomList: List<LotRoom>?): ParkingLotListModel {
        var lotList = mutableListOf<ParkingLotModel>()
        lotRoomList?.forEach { lot ->
            lotList.add(ParkingLotModel(lot.parkingId))
        }
        return ParkingLotListModel(lotList)
    }

    fun reservationRoomListToReservationListModel(reservationRoomList: List<ReservationRoom>?): ReservationListModel{
        var reservationListModel = mutableListOf<ReservationModel>()
        reservationRoomList?.forEach { reservation ->
            reservationListModel.add(ReservationModel(reservation.id,
                                                      reservation.authorizationCode,
                                                      reservation.start,
                                                      reservation.end,
                                                      reservation.parkingLot))
                                            }
        return ReservationListModel(reservationListModel)
    }

    fun reservationModelToReservationRoom(reservationModel: ReservationModel): ReservationRoom{
        return ReservationRoom(reservationModel.id,
                               reservationModel.authorizationCode,
                               reservationModel.startDate,
                               reservationModel.endDate,
                               reservationModel.parkingLot)
    }

}
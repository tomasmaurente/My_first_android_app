package com.example.domain.entities

data class ParkingLotListModel(var lotList: List<ParkingLotModel>,
                               var owner: String?,
                               var parkingSize: Int){

}

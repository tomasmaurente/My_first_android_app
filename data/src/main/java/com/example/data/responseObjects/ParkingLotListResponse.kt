package com.example.data.responseObjects

import java.io.Serializable

data class ParkingLotListResponse(var lotList: List<ParkingLotResponse>,
                                  var owner: String?,
                                  var parkingSize: Int
                                  ) : Serializable

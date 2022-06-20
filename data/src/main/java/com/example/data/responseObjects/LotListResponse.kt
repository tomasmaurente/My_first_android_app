package com.example.data.responseObjects

import java.io.Serializable

data class LotListResponse(var lotList: List<LotResponse>,
                           var owner: String?,
                           var parkingSize: Int
                           ) : Serializable

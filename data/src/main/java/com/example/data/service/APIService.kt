package com.example.data.service

import com.example.data.responseObjects.ParkingLotListResponse
import com.example.data.responseObjects.ReservationListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("{parkingId}/reservations.json")
    suspend fun getReservationList(@Path("parkingId") id: String) : Response<ReservationListResponse>

    @GET ("{parkingId}/reservations.json")
    suspend fun getParkingLotList(@Path("parkingId") id: String) : Response<ParkingLotListResponse>
}
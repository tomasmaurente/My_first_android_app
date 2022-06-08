package com.example.data.service

import com.example.data.responseObjects.ParkingLotListResponse
import com.example.data.responseObjects.ReservationListResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @GET("{parkingId}/reservations.json")
    suspend fun getReservationList(@Path("parkingId") id: String) : Response<ReservationListResponse>

    @GET ("parkings/{parkingId}/.json")
    suspend fun getParkingLotList(@Path("parkingId") id: String) : Response<ParkingLotListResponse>

    @DELETE("{parkingId}/reservations/{reservationId}/.json")
    suspend fun deleteReservation(@Path("parkingId") id: String, @Path("reservationId") resId: String)



}
package com.example.data.service

import com.example.data.responseObjects.LotListResponse
import com.example.data.responseObjects.ReservationListResponse
import com.example.data.responseObjects.ReservationRequest
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("{parkingId}/reservations.json")
    suspend fun getReservationList(@Path("parkingId") id: String) : Response<ReservationListResponse>

    @GET ("parkings/{parkingId}/.json")
    suspend fun getLotList(@Path("parkingId") id: String) : Response<LotListResponse>

    @DELETE("{parkingId}/reservations/{reservationId}/.json")
    suspend fun deleteReservation(@Path("parkingId") id: String, @Path("reservationId") resId: String) : Response<Any>

    @POST("{parkingId}/reservations.json?=")
    suspend fun postReservation(@Path("parkingId") id: String, @Body newReservation: ReservationRequest) : Response<Any>
}
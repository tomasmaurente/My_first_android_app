package com.example.data.service

import com.example.data.responseObjects.LotListResponse
import com.example.data.responseObjects.ReservationListResponse
import com.example.data.responseObjects.ReservationRequest
import com.example.domain.entities.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ParkingService (private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    private val parkingId: String = "-N0TUDrXZUxA_wbd391E"

    suspend fun getLotList() : Result<LotListResponse?> {
        var result : Result<LotListResponse?>

        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).getLotList(parkingId)
                if(callResponse.isSuccessful){
                    Result.Success((callResponse.body()))
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }

    suspend fun getReservationList() : Result<ReservationListResponse?> {
        var result : Result<ReservationListResponse?>

        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).getReservationList(parkingId)
                if(callResponse.isSuccessful){
                    Result.Success((callResponse.body()))
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }

    suspend fun deleteReservation(reservationId: String): Result<Boolean>{
        var result : Result<Boolean>
        withContext(dispatcher){
        result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).deleteReservation(parkingId,reservationId)
                if(callResponse.isSuccessful){
                    Result.Success(true)
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }

    suspend fun addReservation(reservation: ReservationRequest) : Result<Boolean> {
        var result : Result<Boolean>
        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).postReservation(parkingId,reservation)
                if(callResponse.isSuccessful){
                    Result.Success(true)
                }
                else {
                    Result.Failure(Exception(callResponse.message()))
                }
            }
            catch(e: Exception){
                Result.Failure(e)
            }
        }
        return result
    }



}
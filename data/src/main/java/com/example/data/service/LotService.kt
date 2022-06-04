package com.example.data.service

import com.example.data.responseObjects.ParkingLotListResponse
import com.example.parkinglottest.RetrofitFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LotService (private val dispatcher: CoroutineDispatcher = Dispatchers.IO){

    suspend fun getLots(parkingId: String) : Result<ParkingLotListResponse?> {
        var result : Result<ParkingLotListResponse?>

        withContext(dispatcher){
            result = try {
                val callResponse = RetrofitFactory.getRetrofit().create(APIService::class.java).getParkingLotList(parkingId)
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
}
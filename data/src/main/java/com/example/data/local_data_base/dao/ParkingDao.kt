package com.example.data.local_data_base.dao


import androidx.room.*
import com.example.data.local_data_base.entities.LotRoom
import com.example.data.local_data_base.entities.ReservationRoom

@Dao
interface ParkingDao {

    @Query("SELECT * FROM lotList")
    fun findLotList(): List<LotRoom>?

    @Query("SELECT * FROM reservationList")
    fun findReservationList(): List<ReservationRoom>?

    @Query("SELECT * FROM lotList WHERE parkingId = :parkingId")
    fun findByParkingLot(parkingId: String): ReservationRoom?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewReservation(reservation: ReservationRoom)

    @Delete()
    suspend fun deleteReservation(reservationId: String)
}
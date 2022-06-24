package com.example.data.local_data_base.dao

import androidx.room.*
import com.example.data.local_data_base.entities.ReservationRoom

@Dao
interface ReservationDao {

    @Query("SELECT * FROM reservationList")
    suspend fun findReservationList(): List<ReservationRoom>

    @Query("SELECT * FROM reservationList WHERE parkingLot = :parkingLot")
    suspend fun findReservationList(parkingLot: Int): List<ReservationRoom>

    // FIXME: This could return an Int to indicate if rows were inserted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewReservation(reservation: ReservationRoom)

    // FIXME: This could receive a Reservation entity, use @Delete and return an Int to indicate if
    //  rows where deleted.
    @Query("DELETE FROM reservationList WHERE id  = :reservationId")
    suspend fun deleteReservation(reservationId: String)
}
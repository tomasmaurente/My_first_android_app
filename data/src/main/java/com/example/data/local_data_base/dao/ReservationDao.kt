package com.example.data.local_data_base.dao

import androidx.room.*
import com.example.data.local_data_base.entities.LotRoom
import com.example.data.local_data_base.entities.ReservationRoom

@Dao
interface ReservationDao {

    @Query("SELECT * FROM reservationList")
    suspend fun findReservationList(): List<ReservationRoom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewReservation(reservation: ReservationRoom)

    //@Delete()
    //suspend fun deleteReservation(reservationId: String)
}
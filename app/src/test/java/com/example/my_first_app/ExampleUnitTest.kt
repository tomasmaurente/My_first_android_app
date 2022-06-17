package com.example.my_first_app

import android.content.Context
import com.example.data.local_data_base.ReservationDataBase
import com.example.data.repositories.ReservationRepositoryImp
import com.example.data.service.ParkingService
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var addRepo: ReservationRepositoryImp
    @M
    lateinit var context: Context

    @Before
    fun setUp(){
        context =
        addRepo = ReservationRepositoryImp(ParkingService(),
                                           ReservationDataBase.getInstance(context = ))
    }
    @Test
    fun addReservationAddsToDataBase() {
        assertEquals(4, 2 + 2)
    }
}
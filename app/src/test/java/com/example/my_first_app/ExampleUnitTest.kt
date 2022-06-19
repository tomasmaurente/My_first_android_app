package com.example.my_first_app

import android.content.Context
import com.example.data.repositories.AddRepositoryImp
import com.example.data.repositories.ReservationRepositoryImp
import com.example.domain.entities.Reservation
import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.ReservationModel
import com.example.domain.entities.Result
import com.example.domain.repositories.AddRepository
import com.example.domain.repositories.ReservationRepository
import com.example.domain.usecases.AddUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @MockK
    lateinit var addRepo: AddRepository
    lateinit var addUseCase: AddUseCase
    private lateinit var reservation: Reservation
    private lateinit var reservationList: ReservationListModel
    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        addUseCase = AddUseCase()
        addUseCase.addReservationRepository = addRepo
        reservation =  Reservation()
        reservationList = ReservationListModel(listOf())
    }
    @Test
    fun addReservationAddsToDataBase() {
        coEvery{ addRepo.addReservation(reservation)} answers
                {Result.Success(false)}
        runBlocking { addUseCase( reservation, reservationList) }
        coVerify (exactly = 1){ addRepo.addReservation(reservation) } // CoVerify se fija si el repo es llamado
    }

}
package com.example.my_first_app

import android.content.Context
import com.example.data.repositories.AddRepositoryImp
import com.example.data.repositories.ReservationRepositoryImp
import com.example.domain.entities.Reservation
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

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        addUseCase = AddUseCase()
        addUseCase.addReservationRepository = addRepo
    }
    @Test
    fun addReservationAddsToDataBase() {
        coEvery{ addRepo.addReservation("lsdfjbvbls", Reservation(), true)} answers
                {Result.Success(true)}
        runBlocking { addUseCase("lsdfjbvbls", Reservation(), true) }
        coVerify (exactly = 1){ addRepo.addReservation("lsdfjbvbls", Reservation(), true) } // CoVerify se fija si el repo es llamado
    }

}
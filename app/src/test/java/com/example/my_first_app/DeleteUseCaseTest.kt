package com.example.my_first_app

import com.example.domain.entities.Reservation
import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteRepository
import com.example.domain.usecases.AddUseCase
import com.example.domain.usecases.DeleteUseCase
import com.example.domain.utils.AddPossibilities
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException

class DeleteUseCaseTest {

    @RelaxedMockK
    private lateinit var deleteRepository: DeleteRepository

    lateinit var deleteUseCase: DeleteUseCase

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        deleteUseCase = DeleteUseCase(deleteRepository)
    }

    @Test
    fun correctDelete() = runBlocking {

        var reservation = Reservation("",0,1,"hello World",0)
        coEvery { deleteRepository.deleteReservation(reservation) } returns  Result.Success(true)

        deleteUseCase(reservation,"hello World")

        coVerify (exactly = 1 ) { deleteRepository.deleteReservation(reservation) }
    }

    @Test
    fun incorrectDelete() = runBlocking {

        var reservation = Reservation("",0,1,"hello World",0)
        coEvery { deleteRepository.deleteReservation(reservation) } returns  Result.Failure(RuntimeException())

        deleteUseCase(reservation,"wrong authorization code")

        coVerify (exactly = 0 ) { deleteRepository.deleteReservation(reservation) }
    }


}
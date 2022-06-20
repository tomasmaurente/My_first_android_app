package com.example.my_first_app

import com.example.domain.entities.ParkingLotListModel
import com.example.domain.entities.Reservation
import com.example.domain.entities.ReservationListModel
import com.example.domain.entities.Result
import com.example.domain.repositories.DeleteRepository
import com.example.domain.repositories.LotRepository
import com.example.domain.usecases.LotUseCase
import com.example.domain.utils.AddPossibilities
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LotUseCaseTest {

    @RelaxedMockK
    private lateinit var lotRepository: LotRepository
    private lateinit var lotUseCase: LotUseCase

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        lotUseCase = LotUseCase(lotRepository)
    }

    @Test
    fun getLotListFromDataBase() = runBlocking {
        coEvery { lotRepository.getLotList(true) } returns Result.Success(ParkingLotListModel(listOf()))

        var result = lotUseCase(true)

        assert( result == Result.Success(ParkingLotListModel(listOf())))
        coVerify (exactly = 1 ) { lotRepository.getLotList(true) }
    }

    @Test
    fun getLotListFromService() = runBlocking {
        coEvery { lotRepository.getLotList(false) } returns Result.Success(ParkingLotListModel(listOf()))

        var result = lotUseCase(false)

        assert( result == Result.Success(ParkingLotListModel(listOf())))
        coVerify (exactly = 1 ) { lotRepository.getLotList(false) }
    }
}
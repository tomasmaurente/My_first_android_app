package com.example.my_first_app

import com.example.domain.repositories.AddRepository
import com.example.domain.usecases.AddUseCase
import org.junit.Before
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK

internal class AddUseCaseTest{

    @RelaxedMockK
    private lateinit var addRepository: AddRepository

    lateinit var addUseCase: AddUseCase

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        addUseCase = AddUseCase(addRepository)
    }
}
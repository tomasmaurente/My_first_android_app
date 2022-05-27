package com.example.domain.usecases

import com.example.domain.repositories.GetLotListRepository

class GetLotListUseCase {
    lateinit var getLotListRepository: GetLotListRepository
    operator fun invoke() = getLotListRepository.getLotList()
}
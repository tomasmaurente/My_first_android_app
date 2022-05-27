package com.example.domain.repositories

import com.example.domain.entities.Lot

interface GetLotListRepository {
    fun getLotList(): List<Lot>
}
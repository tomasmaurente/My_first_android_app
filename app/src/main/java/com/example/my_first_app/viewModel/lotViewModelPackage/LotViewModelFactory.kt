package com.example.my_first_app.viewModel.lotViewModelPackage


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.data.local_data_base.LotDataBase
import com.example.data.local_data_base.ReservationDataBase
import com.example.data.repositories.GetLotListRepositoryImp
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.data.service.ParkingService
import com.example.domain.usecases.GetLotListUseCase
import com.example.domain.usecases.GetReservationListUseCase

class LotViewModelFactory(private val context: Context) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == LotViewModel::class.java) {
            LotViewModel(GetLotListUseCase().apply {
                getLotListRepository = GetLotListRepositoryImp(
                    ParkingService(),
                    LotDataBase.getInstance(context)
            ) }
            , GetReservationListUseCase().apply {
                    getReservationListRepository = GetReservationListRepositoryImp(
                        ParkingService(),
                        ReservationDataBase.getInstance(context)
                    )
            } ) as T
        } else {
            super.create(modelClass)
        }
    }
}
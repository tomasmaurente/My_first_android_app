package com.example.my_first_app.viewModel.lotViewModelPackage


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.data.local_data_base.LotDataBase
import com.example.data.local_data_base.ReservationDataBase
import com.example.data.repositories.AddRepositoryImp
import com.example.data.repositories.LotRepositoryImp
import com.example.data.repositories.ReservationRepositoryImp
import com.example.data.service.ParkingService
import com.example.domain.usecases.AddUseCase
import com.example.domain.usecases.LotUseCase
import com.example.domain.usecases.ReservationUseCase

class LotViewModelFactory(private val context: Context) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == LotViewModel::class.java) {
            LotViewModel(LotUseCase().apply {
                lotRepository = LotRepositoryImp(
                    ParkingService(),
                    LotDataBase.getInstance(context),
                    AddUseCase().apply {
                        addReservationRepository = AddRepositoryImp(
                            ParkingService(),
                            LotDataBase.getInstance(context),
                            ReservationDataBase.getInstance(context)
                        )
                    }
                )
            }
            , ReservationUseCase().apply {
                getReservationListRepository = ReservationRepositoryImp(
                    ParkingService(),
                    ReservationDataBase.getInstance(context),
                    AddUseCase().apply {
                        addReservationRepository = AddRepositoryImp(
                            ParkingService(),
                            LotDataBase.getInstance(context),
                            ReservationDataBase.getInstance(context)
                        )
                    }
                )
            }
            ) as T
        } else {
            super.create(modelClass)
        }
    }
}
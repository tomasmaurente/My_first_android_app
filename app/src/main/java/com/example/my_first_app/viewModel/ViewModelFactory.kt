package com.example.my_first_app.viewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.data.local_data_base.ParkingDataBase
import com.example.data.repositories.AddRepositoryImp
import com.example.data.repositories.DeleteRepositoryImp
import com.example.data.repositories.LotRepositoryImp
import com.example.data.repositories.ReservationRepositoryImp
import com.example.data.service.ParkingService
import com.example.domain.usecases.AddUseCase
import com.example.domain.usecases.DeleteUseCase
import com.example.domain.usecases.LotUseCase
import com.example.domain.usecases.ReservationUseCase
import com.example.my_first_app.viewModel.addViewModelPackage.AddViewModel
import com.example.my_first_app.viewModel.lotViewModelPackage.LotViewModel
import com.example.my_first_app.viewModel.reservationsViewModelPackage.ReservationViewModel

class ViewModelFactory(private val context: Context) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LotViewModel::class.java -> {
                LotViewModel(LotUseCase(LotRepositoryImp(
                    ParkingService(),
                    ParkingDataBase.getInstance(context)
                )).apply {
                },
                    ReservationUseCase(ReservationRepositoryImp(
                        ParkingService(),
                        ParkingDataBase.getInstance(context)
                    )).apply {
                    }
                ) as T
            }
            ReservationViewModel::class.java -> {
                ReservationViewModel(DeleteUseCase(DeleteRepositoryImp(
                    ParkingService(),
                    ParkingDataBase.getInstance(context)
                )).apply {
                }) as T
            }
            AddViewModel::class.java -> {
                AddViewModel(AddUseCase(AddRepositoryImp(
                    ParkingService(),
                    ParkingDataBase.getInstance(context)
                    )
                ).apply {},
                    ParkingDataBase.getInstance(context)
                ) as T
            }
            else -> {
                super.create(modelClass)
            }
        }
    }
}
package com.example.my_first_app.viewModel.reservationsViewModelPackage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.data.repositories.GetReservationListRepositoryImp
import com.example.domain.usecases.GetReservationListUseCase

class ReservationViewModelFactory(private val context: Context) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == ReservationViewModel::class.java) {
            ReservationViewModel(GetReservationListUseCase().apply {
                getReservationListRepository = GetReservationListRepositoryImp()
            }) as T
        } else {
            super.create(modelClass)
        }
    }

}
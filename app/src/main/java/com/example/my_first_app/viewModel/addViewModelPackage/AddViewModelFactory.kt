package com.example.my_first_app.viewModel.addViewModelPackage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.data.repositories.AddReservationRepositoryImp
import com.example.domain.usecases.AddReservationUseCase

class AddViewModelFactory(private val context: Context) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AddViewModel::class.java -> {
                AddViewModel(AddReservationUseCase().apply {
                    addReservationRepository = AddReservationRepositoryImp()
                }) as T
            }
            else -> super.create(modelClass)
        }
    }
}
        /*return if (modelClass == AddViewModel::class.java) {
            AddViewModel(AddReservationUseCase().apply {
                addReservationRepository =AddReservationRepositoryImp()
            }) as T
        } else {
            super.create(modelClass)
        }*/

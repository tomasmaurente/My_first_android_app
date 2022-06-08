package com.example.my_first_app.viewModel.lotViewModelPackage


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.data.repositories.GetLotListRepositoryImp
import com.example.domain.usecases.GetLotListUseCase

class LotViewModelFactory(private val context: Context) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == LotViewModel::class.java) {
            LotViewModel(GetLotListUseCase().apply {
                getLotListRepository = GetLotListRepositoryImp()
            }) as T
        } else {
            super.create(modelClass)
        }
    }

}
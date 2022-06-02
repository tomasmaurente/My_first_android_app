package com.example.my_first_app.viewModel.lotViewModelPackage

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class LotViewModelProvider(activity: FragmentActivity?) : ViewModelProvider(
    (activity as ViewModelStoreOwner),
    LotViewModelFactory(activity.applicationContext)
)
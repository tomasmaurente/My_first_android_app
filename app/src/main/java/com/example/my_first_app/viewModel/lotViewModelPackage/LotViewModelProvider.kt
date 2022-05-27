package com.example.my_first_app.viewModel.lotViewModelPackage

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class LotViewModelProvider(activity: Activity) : ViewModelProvider(
    (activity as ViewModelStoreOwner),
    LotViewModelFactory(activity.applicationContext)
)
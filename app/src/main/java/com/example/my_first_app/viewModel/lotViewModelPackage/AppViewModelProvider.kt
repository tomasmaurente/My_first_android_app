package com.example.my_first_app.viewModel.lotViewModelPackage

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class AppViewModelProvider(activity: Activity) : ViewModelProvider(
    (activity as ViewModelStoreOwner),
    AppViewModelFactory(activity.applicationContext)
)
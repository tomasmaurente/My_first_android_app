package com.example.my_first_app.viewModel.addViewModelPackage

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class AddViewModelProvider(activity: Activity) : ViewModelProvider(
    (activity as ViewModelStoreOwner),
    AddViewModelFactory(activity.applicationContext)
)
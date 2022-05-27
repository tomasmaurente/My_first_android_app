package com.example.my_first_app.viewModel.reservationsViewModelPackage

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ReservationViewModelProvider(activity: Activity) : ViewModelProvider(
    (activity as ViewModelStoreOwner),
    ReservationViewModelFactory(activity.applicationContext)
)
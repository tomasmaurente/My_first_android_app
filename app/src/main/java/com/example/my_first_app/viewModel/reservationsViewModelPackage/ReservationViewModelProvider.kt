package com.example.my_first_app.viewModel.reservationsViewModelPackage

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ReservationViewModelProvider(activity: FragmentActivity?) : ViewModelProvider(
    (activity as ViewModelStoreOwner),
    ReservationViewModelFactory(activity.applicationContext)
)
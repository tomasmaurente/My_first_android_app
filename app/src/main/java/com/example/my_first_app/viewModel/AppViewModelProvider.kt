package com.example.my_first_app.viewModel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.my_first_app.viewModel.ViewModelFactory

class AppViewModelProvider(activity: FragmentActivity?) : ViewModelProvider(
    (activity as ViewModelStoreOwner),
    ViewModelFactory(activity.applicationContext)
)
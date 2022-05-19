package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.my_first_app.R
import com.example.my_first_app.databinding.LayoutParkingLotsBinding
import com.example.my_first_app.databinding.LayoutReservationsBinding

class ReservationsFragment: Fragment(R.layout.layout_reservations) {

    private lateinit var binding: LayoutReservationsBinding
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LayoutReservationsBinding.bind(view)
    }
}
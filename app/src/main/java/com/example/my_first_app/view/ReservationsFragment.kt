package com.example.my_first_app.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.my_first_app.R
import com.example.my_first_app.databinding.FragmentReservationsBinding

class ReservationsFragment: Fragment(R.layout.fragment_reservations) {

    private lateinit var binding: FragmentReservationsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReservationsBinding.bind(view)

    }
}
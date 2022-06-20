package com.example.my_first_app.utils

import com.example.domain.entities.Reservation

interface DeleteDialogCallBack {
    fun onDeleteClicked(authorizationCode: String, reservation: Reservation)
}
package com.example.domain.entities

data class LotReservation(  val start: String,
                             val start_day:  String,
                             val start_month_year: String,
                             val end_time: String,
                             val end_day: String,
                             val end_month_year: String,
                             val delete_code: Int
                          )


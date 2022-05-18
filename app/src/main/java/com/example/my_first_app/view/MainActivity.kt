package com.example.my_first_app.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_first_app.R
import com.example.my_first_app.adapters.parking_lot_adapter
import com.example.my_first_app.model.data_clases.parking_provider
import com.example.my_first_app.databinding.ContentMainBinding
import com.example.my_first_app.model.objects.parking_lot

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
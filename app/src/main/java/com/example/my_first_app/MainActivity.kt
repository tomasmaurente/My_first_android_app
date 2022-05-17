package com.example.my_first_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_first_app.adapters.parking_lot_adapter
import com.example.my_first_app.data_clases.parking_provider
import com.example.my_first_app.databinding.ContentMainBinding
import com.example.my_first_app.objects.parking_lot

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    fun initRecyclerView(){
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = parking_lot_adapter(parking_provider.spots) { parkingSpot ->
            onParkingSpotSelected(
                parkingSpot
            )
        }
    }

    fun onParkingSpotSelected(parkingLots: parking_lot){
        //Toast.makeText(this,parkingLots.spot, Toast.LENGTH_SHORT).show()
    }
}
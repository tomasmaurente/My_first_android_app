package com.example.my_first_app

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.my_first_app.adapters.parking_lot_adapter
import com.example.my_first_app.databinding.ActivityMainBinding
import com.example.my_first_app.databinding.ContentMainBinding

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

    fun onParkingSpotSelected(parkingLots: parking_lots){
        Toast.makeText(this,parkingLots.spot, Toast.LENGTH_SHORT).show()
    }
}
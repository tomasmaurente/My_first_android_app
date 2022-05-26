package com.example.my_first_app.adapters.lotAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.ParkingLot
import com.example.my_first_app.R

class ParkingLotAdapter (private val itemsList: List<ParkingLot>, private val onClickListener:(ParkingLot)-> Unit): RecyclerView.Adapter<ParkingLotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingLotViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ParkingLotViewHolder(layoutInflater.inflate(R.layout.layout_lot_item, parent, false))
    }

    override fun onBindViewHolder(holder: ParkingLotViewHolder, position: Int) {
        val item = itemsList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = itemsList.size

}
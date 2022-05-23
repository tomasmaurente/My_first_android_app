package com.example.my_first_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_app.R
import com.example.my_first_app.model.objects.ParkingLot

class ParkingLotAdapter (private val itemsList: List<ParkingLot>, private val onClickListener:(ParkingLot)-> Unit): RecyclerView.Adapter<ParkingLotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingLotViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ParkingLotViewHolder(layoutInflater.inflate(R.layout.main_recycle_view_item_with_content, parent, false))
    }

    override fun onBindViewHolder(holder: ParkingLotViewHolder, position: Int) {
        val item = itemsList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = itemsList.size

}
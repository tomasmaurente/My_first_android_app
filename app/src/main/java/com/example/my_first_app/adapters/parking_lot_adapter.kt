package com.example.my_first_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_app.R
import com.example.my_first_app.model.objects.ParkingLot

class ParkingLot_adapter (private val itemsList: List<ParkingLot>, private val onClickListener:(ParkingLot)-> Unit): RecyclerView.Adapter<ParkingLot_view_holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingLot_view_holder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ParkingLot_view_holder(layoutInflater.inflate(R.layout.main_recycle_view_item_with_content, parent, false))
    }

    override fun onBindViewHolder(holder: ParkingLot_view_holder, position: Int) {
        val item = itemsList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = itemsList.size

}
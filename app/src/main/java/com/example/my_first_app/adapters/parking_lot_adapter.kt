package com.example.my_first_app.adapters

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_first_app.R
import com.example.my_first_app.parking_lots

class parking_lot_adapter (private val itemsList: List<parking_lots>, private val onClickListener:(parking_lots)-> Unit): RecyclerView.Adapter<parking_lot_view_holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): parking_lot_view_holder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return parking_lot_view_holder(layoutInflater.inflate(R.layout.main_recycle_view_item_with_content, parent, false))
    }

    override fun onBindViewHolder(holder: parking_lot_view_holder, position: Int) {
        val item = itemsList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = itemsList.size

}
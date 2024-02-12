package com.example.bptool

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataItemAdapterNestedCombined(var context: Context, var list: List<String>) : RecyclerView.Adapter<DataItemAdapterNestedCombined.ViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // XML Design
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_nested, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int) {
        holder.title.text = list[position]
    }

    class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.title)

    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}
package com.example.bptool

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataItemAdapterNested(var context: Context, var list: List<CombinedTitle>) : RecyclerView.Adapter<DataItemAdapterNested.ViewHolder>()  {
    private var mExpandedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // XML Design
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_data, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int) {
        holder.title.text = list!![position].title

        val isExpanded = position === mExpandedPosition
        holder.recyclerview.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)
        holder.itemView.isActivated = isExpanded
        holder.title.setOnClickListener {
            print("Enter in ")
            holder.recyclerview.layoutManager = LinearLayoutManager(context)
            // val adapter = DataItemAdapterNested(context , list.get(position).mode)
            val adapter = DataItemAdapterNestedCombined(context , list.get(position).combined)
            holder.recyclerview.adapter = adapter

            mExpandedPosition = if (isExpanded) -1 else position
            notifyDataSetChanged()

        }


    }

    class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        var recyclerview = itemView.findViewById<RecyclerView>(R.id.recyclerviewNested)
        var title = itemView.findViewById<TextView>(R.id.title)


    }

    override fun getItemCount(): Int {
//         if (list.size >=1){
//            return  0
//        }else{
        return list!!.size
        //}

    }
}
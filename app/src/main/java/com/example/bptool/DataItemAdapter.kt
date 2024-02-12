package com.example.bptool

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataItemAdapter(var context: Context, private var list: ArrayList<ListTitle>) : RecyclerView.Adapter<DataItemAdapter.ViewHolder>()  {
    private var mExpandedPosition = -1
   /* private var listitem = ArrayList<String>()
     private val lazyList by lazy {
         listitem

     }*/
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataItemAdapter.ViewHolder {
       // XML Design
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_data, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DataItemAdapter.ViewHolder,
        position: Int
    ) {
        holder.title.text = list[position].title
        val isExpanded = position === mExpandedPosition
        holder.recyclerview.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)
       holder.itemView.isActivated = isExpanded
        holder.recyclerview.layoutManager = LinearLayoutManager(context)
        holder.title.setOnClickListener {
            for (i in 0 until list.get(position).combined.size){
                if (!list.get(position).modeHash){
                   // lazyList.addAll(list.get(position).mode!!)
                    val adapter = MDHashAdapter(context , list.get(position).mode!!)
                  //  val adapter = MDHashAdapter(context , lazyList)
                    holder.recyclerview.adapter = adapter
                }else{
                    val adapter = DataItemAdapterNested(context , list.get(position).combined)
                    holder.recyclerview.adapter = adapter
                }
            }
            mExpandedPosition = if (isExpanded) -1 else position
            notifyDataSetChanged()

        }
    }

    class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        var recyclerview = itemView.findViewById<RecyclerView>(R.id.recyclerviewNested)
        var title = itemView.findViewById<TextView>(R.id.title)


    }

    override fun getItemCount(): Int {
            return list.size
    }
}
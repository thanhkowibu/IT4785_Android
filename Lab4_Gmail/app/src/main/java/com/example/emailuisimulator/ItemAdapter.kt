package com.example.emailuisimulator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: List<ItemData>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // ViewHolder for the item layout
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderName: TextView = itemView.findViewById(R.id.SenderName)
        val sendTime: TextView = itemView.findViewById(R.id.SendTime)
        val summary: TextView = itemView.findViewById(R.id.Summary)
        val avatar: TextView = itemView.findViewById(R.id.Avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.senderName.text = item.senderName
        holder.sendTime.text = item.sendTime
        holder.summary.text = item.summary
        holder.avatar.text = item.senderName.first().toString()
    }

    override fun getItemCount(): Int = items.size
}

package com.example.testwebsocket

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatReceiveViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
    val itemReceiveMessageText: TextView = itemView.findViewById(R.id.itemReceiveMessageText)
}
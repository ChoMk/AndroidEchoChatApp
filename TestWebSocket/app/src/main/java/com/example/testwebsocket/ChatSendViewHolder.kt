package com.example.testwebsocket

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatSendViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    val itemSendMessageText:TextView = itemView.findViewById(R.id.itemSendMessageText)
}
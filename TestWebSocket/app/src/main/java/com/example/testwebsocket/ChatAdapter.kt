package com.example.testwebsocket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val messageList = ArrayList<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == SEND_TYPE) {
            ChatSendViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_send_message,
                    parent,
                    false
                )
            )
        } else {
            ChatReceiveViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_receive_message,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (messageList[position].type == SEND_TYPE) {
            (holder as ChatSendViewHolder).itemSendMessageText.text = messageList[position].contents
        } else {
            (holder as ChatReceiveViewHolder).itemReceiveMessageText.text =
                messageList[position].contents
        }
    }

    override fun getItemViewType(position: Int): Int {
        return messageList[position].type
    }
    fun addMessageList(message:Message){
        messageList.add(message)
        notifyDataSetChanged()
    }
}
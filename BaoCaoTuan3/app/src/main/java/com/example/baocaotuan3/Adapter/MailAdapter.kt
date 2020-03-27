package com.example.baocaotuan3.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baocaotuan3.Model.Messages
import com.example.baocaotuan3.R

class MailAdapter internal constructor(val messages: ArrayList<Messages>) : RecyclerView.Adapter<MailAdapter.MessageViewHolder>() {


    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val mailuser = messages[position]
        holder.itemView.findViewById<TextView>(R.id.id).text = mailuser.id
        holder.itemView.findViewById<TextView>(R.id.from).text = mailuser.from
        holder.itemView.findViewById<TextView>(R.id.email).text = mailuser.email
        holder.itemView.findViewById<TextView>(R.id.subject).text = mailuser.subject
        holder.itemView.findViewById<TextView>(R.id.message).text = mailuser.message
        holder.itemView.findViewById<TextView>(R.id.date).text = mailuser.date
    }

    override fun getItemCount() = messages.count()
}


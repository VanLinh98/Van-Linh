package com.example.baocaotuan3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baocaotuan3.Model.Message
import com.example.baocaotuan3.R
import kotlinx.android.synthetic.main.item_layout.view.*

class MailAdapter(private var maillist : MutableList<Message>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return maillist.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val mail = maillist[position]
        holder.itemView.txtfrom.text = maillist.get(position).from
        holder.itemView.txtsubject.text = maillist.get(position).subject
        holder.itemView.txtdate.text = maillist.get(position).date
    }
}

class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){

}
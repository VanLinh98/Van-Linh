package com.example.myapplication.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.CityModel
import com.example.myapplication.R
import com.example.myapplication.Activity.Activity
import kotlinx.android.synthetic.main.item_recycleview.view.*

class AdapterHistory (var models: List<CityModel>) : RecyclerView.Adapter<AdapterHistory.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.findViewById<TextView>(R.id.textView2)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val Context : Context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycleview, parent, false)
        val MessageViewHolder = ViewHolder(itemView)

        if(MessageViewHolder.itemView.linearitem!=null)
        {
            MessageViewHolder.itemView.setOnClickListener {
                var Model : CityModel = models.get(MessageViewHolder.adapterPosition)
                val Intent = Intent(Context, Activity::class.java)
                Intent.putExtra("city",Model)
                Context.startActivity(Intent)
            }
        }
        return MessageViewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = models[position]
        holder.id.text = current.City
    }

    override fun getItemCount() = models.count()

    internal fun setWords(models: List<CityModel>) {
        this.models=models
        notifyDataSetChanged()
    }
}
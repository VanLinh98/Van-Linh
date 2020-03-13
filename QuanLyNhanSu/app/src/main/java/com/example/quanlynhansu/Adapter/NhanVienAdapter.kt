package com.example.quanlynhansu.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.quanlynhansu.R

class NhanVienAdapter(var context : Context, var mangnv : ArrayList<NhanVien>):BaseAdapter() {

    class ViewHolder( row : View){
        var textview : TextView
        var imageview : ImageView

        init {
            textview = row.findViewById(R.id.txtitem) as TextView
            imageview = row.findViewById(R.id.imgitem) as ImageView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View?
        var viewholde : ViewHolder
        if(convertView == null){
            var layoutinflater : LayoutInflater = LayoutInflater.from(context)
            view = layoutinflater.inflate(R.layout.listview_layout, null)
            viewholde =
                ViewHolder(view)
            view.tag =viewholde
        }else{
            view =convertView
            viewholde = convertView.tag as ViewHolder
        }
            var nv : NhanVien = getItem(position) as NhanVien
            viewholde.textview.text = nv.id.plus("-").plus(nv.name)
            if (nv.gender){
                viewholde.imageview.setImageResource(R.drawable.ic_girl)
            }else{
                viewholde.imageview.setImageResource(R.drawable.ic_boy)
            }

        return view as View
    }

    override fun getItem(position: Int): Any {
        return mangnv.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return mangnv.size
    }
}
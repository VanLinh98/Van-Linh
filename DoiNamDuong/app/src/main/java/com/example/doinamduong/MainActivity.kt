package com.example.doinamduong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    fun chuyendoi(){
        var nam = (editnam.text.toString()).toInt()
        var x = nam -3
        var y = x%10
        var z = x%12

        var Can : List<String> = listOf("Quý","Giáp","Ất","Bính","Đinh","Mậu","Kỷ","Canh","Tân","Nhâm")
        var Chi : List<String> = listOf("Hợi","Tý","Sửu","Dần","Mẹo","Thìn","Tỵ","Ngọ","Mùi","Thân","Dậu","Tuất")

        var s  = Can.get(y)
        var c  = Chi.get(z)
        s=s.plus(" ")
        s=s.plus(c)
        tvhien.text = s.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnchuyendoi.setOnClickListener {
           chuyendoi()
        }
    }
}

package com.example.ttcanhan

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_layout.*
import kotlinx.android.synthetic.main.dialog_layout.view.*


class MainActivity : AppCompatActivity() {

    fun Hopthuthoai() {
        var bang : String = ""
        if(radiocaodang.isChecked)
            bang = radiocaodang.text.toString()
        if(radiotrungcap.isChecked)
            bang = radiotrungcap.text.toString()
        if(radiodaihoc.isChecked)
            bang = radiodaihoc.text.toString()


        var sothich : String = ""
        if(checkdosach.isChecked){
            sothich += checkdosach.text.toString()
        }
        if(checkdocbao.isChecked){
            sothich += "  " + checkdocbao.text.toString()
        }
        if(checkdoccode.isChecked){
            sothich += "  " + checkdoccode.text.toString()
        }
        var msg = edithoten.text.toString() + "\n" + editcmnd.text.toString() + "\n" + bang + "\n" + sothich
        var ttbs = editthongtin.text.toString()


// ĐƯA DỮ LIỆU LÊN ALERT DIALOG
        val mdialog = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null)
        val mbulder = AlertDialog.Builder(this).setView(mdialog)
        val malertdialog = mbulder.show()
        malertdialog.alerthongtin.setText(msg)
        malertdialog.alerthongtinbosung.setText(ttbs)
        mdialog.btndong.setOnClickListener {
            //dismiss dialog
            malertdialog.dismiss()
            Toast.makeText(this, "kết thúc Alert Dialog", Toast.LENGTH_SHORT).show()
        }
    }

    fun kiemtrahople(){
        //kiểm tra họ tên
        var hoten = edithoten.text
        if (hoten.length < 3){
            edithoten.requestFocus()
            edithoten.selectAll()
            Toast.makeText(this, "Tên ít nhất phải có 3 kí tự",Toast.LENGTH_SHORT).show()
            return
        }
        //kiểm tra cmnd
        var cmnd = editcmnd.text
        cmnd = cmnd.trim() as Editable?
        val pattern = "[0-9]*".toRegex()
        if(!(pattern.matches(cmnd) && cmnd.length == 9)){
            editcmnd.requestFocus()
            editcmnd.selectAll()
            Toast.makeText(this, "CMND phải có 9 chữ số", Toast.LENGTH_SHORT).show()
            return
        }
        //kiểm tra sở thích
        if(!checkdosach.isChecked && !checkdocbao.isChecked && !checkdoccode.isChecked){
            Toast.makeText(this,"Chọn ít nhất một sở thích", Toast.LENGTH_SHORT).show()
            return
        }
        Hopthuthoai()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnguithongtin.setOnClickListener {
            kiemtrahople()
        }
    }
}

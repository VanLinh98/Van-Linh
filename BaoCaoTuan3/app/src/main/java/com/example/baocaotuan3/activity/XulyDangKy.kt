package com.example.baocaotuan3.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.baocaotuan3.MainActivity
import com.example.baocaotuan3.R
import com.example.sheek2.Data.UserDatabase
import com.example.sheek2.Data.dataUser
import kotlinx.android.synthetic.main.dangky_layout.*
import kotlinx.coroutines.*
import java.lang.Exception


class XulyDangKy : AppCompatActivity() {
    lateinit var userdata : UserDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dangky_layout)

        btnformdangky.setOnClickListener {
            main()
        }
    }
    fun main(){
      runBlocking{
            insertdata(Dangky())
        }
    }
    fun Dangky(): dataUser {
        var username = edittdn.text
        var pass = editmk.text
        var mail = editemail.text
        var diachi = editdiachi.text
        var gioitinh = false
        if(radio.checkedRadioButtonId == R.id.nu){
            gioitinh = true
        }
        userdata = UserDatabase.getDatabase(this)!!
        val nvObj = dataUser(username.toString(),pass.toString(),mail.toString(),diachi.toString(),gioitinh)
        return nvObj
    }
    suspend fun insertdata(nv : dataUser){
        withContext(Dispatchers.IO){
            try {
                userdata.userdao().insertUser(nv)
                Log.d("AAA", "dang ky thanh cong")
                var x = userdata.userdao().getUsers()
                Log.d("AAA", x.size.toString())
                val intent : Intent = Intent(this@XulyDangKy,
                    MainActivity::class.java)
                startActivity(intent)
            }catch (e : Exception){
                Log.d("AAA", e.toString())
            }

        }
    }
}
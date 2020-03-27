package com.example.baocaotuan3


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.baocaotuan3.activity.XulyDangKy
import com.example.baocaotuan3.activity.XulyMail
import com.example.sheek2.Data.UserDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

open class MainActivity : AppCompatActivity() {

    lateinit var userdata : UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userdata= UserDatabase.getDatabase(this)!!
        btn_dangnhap.setOnClickListener {
            main()
        }
        btn_dangky.setOnClickListener {
            val intent : Intent = Intent(this@MainActivity,
                XulyDangKy::class.java)
            startActivity(intent)
        }
    }

    fun main(){
        runBlocking {
            loadroom()
        }
    }
    suspend fun loadroom (){
        withContext(Dispatchers.IO){
            try {
                var name = et_user_name.text.toString().trim()
                var pass = et_password.text.toString().trim()
                val userdn = userdata.userdao().isDangNhap(name, pass)
                if(userdn.username == name && userdn.password == pass){
                    val xulymail : Intent = Intent(this@MainActivity,
                        XulyMail::class.java)

                        val extras = Bundle()
                        extras.putString("key1", userdn.username)
                        extras.putString("key2", userdn.email)
                        extras.putString("key3", userdn.address)
                        extras.putBoolean("key4", userdn.gender!!)

                        xulymail.putExtras(extras)
                        startActivity(xulymail)
                }else{
                    Log.d("AAA","Thất bại")
                    val x = userdata.userdao().getUsers()
                    Log.d("AAA",x.size.toString())
                }

            }catch (e : Exception){
                Log.d("AAA",e.toString())
            }
        }
    }
 }


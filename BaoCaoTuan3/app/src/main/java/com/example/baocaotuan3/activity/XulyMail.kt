package com.example.baocaotuan3.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.baocaotuan3.Adapter.MailAdapter
import com.example.baocaotuan3.Fragment.FragmentMail
import com.example.baocaotuan3.Fragment.Fragmentprofic
import com.example.baocaotuan3.Model.Profic
import com.example.baocaotuan3.Model.Messages
import com.example.baocaotuan3.R


class XulyMail : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val mailList: List<Messages> = listOf()
    private lateinit var mailAdapter: MailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mail_layout)

    }
    open fun AddMail(view: View){
        val fragmentTransaction = fragmentManager.beginTransaction()
        val firstFragment = FragmentMail()
        fragmentTransaction.replace(R.id.lvprofic, firstFragment)
        fragmentTransaction.commit()
    }

    open fun AddFragment(view : View){

            val fragmentTransaction = fragmentManager.beginTransaction()
            val intent = intent
            val bundle = intent.extras
            val namepr = bundle!!.getString("key1").toString()
            val emailpr = bundle!!.getString("key2").toString()
            val addresspr = bundle!!.getString("key3").toString()
            val genderpr = bundle!!.getBoolean("key4")

            val userprofic = Profic(
                namepr,
                emailpr,
                addresspr,
                genderpr
            )
            val firstFragment = Fragmentprofic(userprofic)

            fragmentTransaction.replace(R.id.lvprofic, firstFragment)
            fragmentTransaction.commit()
    }
}


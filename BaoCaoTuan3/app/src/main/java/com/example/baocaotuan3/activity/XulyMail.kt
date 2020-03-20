package com.example.baocaotuan3.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.baocaotuan3.Adapter.MailAdapter
import com.example.baocaotuan3.Model.Mail
import com.example.baocaotuan3.Model.Message
import com.example.baocaotuan3.R
import kotlinx.android.synthetic.main.mail_layout.*


class XulyMail : AppCompatActivity() {

    private val mailList: MutableList<Message> = mutableListOf()
    private lateinit var mailAdapter: MailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mail_layout)
        imagemail.setOnClickListener {
            this.LoadMail()
        }
    }
    fun LoadMail(){
        mailAdapter = MailAdapter(mailList)

        rv__list_posts.layoutManager = LinearLayoutManager(this)
        rv__list_posts.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        rv__list_posts.adapter = mailAdapter

        AndroidNetworking.initialize(this)//Khởi tạo trong method onCreate() của application:

        //GET Request
        AndroidNetworking.get("https://api.androidhive.info/mail/inbox.json").build().getAsObject(
            Mail::class.java, object : ParsedRequestListener<Mail>{
                override fun onResponse(response: Mail) {
                    mailList.addAll(response.messages)
                    mailAdapter.notifyDataSetChanged()
                }
                override fun onError(anError: ANError?) {
                }
            })
    }
    }


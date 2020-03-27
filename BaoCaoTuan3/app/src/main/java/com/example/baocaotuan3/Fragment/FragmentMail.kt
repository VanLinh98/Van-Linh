package com.example.baocaotuan3.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baocaotuan3.Adapter.MailAdapter
import com.example.baocaotuan3.Model.Messages
import com.example.baocaotuan3.R
import com.example.baocaotuan3.View.MessageView


class FragmentMail: Fragment() {

    lateinit var wordViewModel: MessageView
    lateinit var recycleView: RecyclerView
    var arraylist = ArrayList<Messages>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.loadmail_layout, container, false)

        wordViewModel = ViewModelProvider(this).get(MessageView::class.java)
        arraylist = wordViewModel.main("https://api.androidhive.info/mail/inbox.json") as ArrayList<Messages>

        recycleView = view.findViewById(R.id.loadmail)
        val adapter = MailAdapter(arraylist)
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))

        return view
    }

    fun loadDataItn()
    {

    }
}

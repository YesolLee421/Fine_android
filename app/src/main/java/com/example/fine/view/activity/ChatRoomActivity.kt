package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import com.example.fine.adapter.ChatRoomAdapter
import com.example.fine.presenter.ChatRoomContract
import com.example.fine.presenter.ChatRoomPresenter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_chat_room.*
import java.net.URISyntaxException


class ChatRoomActivity : BaseActivity(), ChatRoomContract.View {
    override val TAG: String = "ChatRoomActivity"
    lateinit var presenter : ChatRoomPresenter

    // 리사이클러뷰 관련 요소
    // 리사이클러뷰
    lateinit var mList: RecyclerView
    // 레이아웃 매니저
    lateinit var lm: LinearLayoutManager
    // 어댑터
    lateinit var mAdapter: ChatRoomAdapter
    // 리스트

    fun setRecyclerView() {
        mList = findViewById(R.id.chat_room_rv)

        lm = LinearLayoutManager(this)
        mList.layoutManager = lm

        mAdapter = ChatRoomAdapter(this)
        mList.adapter = mAdapter
    }

    private var mSocket: Socket? = null


    var case_id: Int = -1

    override fun initPresenter() {
        presenter = ChatRoomPresenter()
        presenter.mContext = this
        presenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        try {
            mSocket = IO.socket("http://10.0.2.2:5000/")
        } catch (e: URISyntaxException) {
            presenter.executionLog(TAG, e.message!!)
        }

        setRecyclerView()

        case_id = intent.getIntExtra("case_id", -1)

        chat_room_btn_send.setOnClickListener {
            val message: String = chat_room_et_input.text.toString()
            if(message!="") {
                presenter.sendMessage(message)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.loadItems(case_id, mAdapter)
    }
}

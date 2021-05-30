package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import com.example.fine.adapter.ChatRoomAdapter
import com.example.fine.presenter.ChatRoomContract
import com.example.fine.presenter.ChatRoomPresenter
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_chat_room.*
import okhttp3.OkHttpClient
import okhttp3.Request
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

    private lateinit var mSocket: Socket
    val SERVER_PATH = "http://10.0.2.2:5000/"
    var case_id: Int = -1

    fun setRecyclerView() {
        mList = findViewById(R.id.chat_room_rv)

        lm = LinearLayoutManager(this)
        mList.layoutManager = lm

        mAdapter = ChatRoomAdapter(this)
        mList.adapter = mAdapter
        presenter.adapter = mAdapter
    }

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
            val message: String = chat_room_et_input.text.toString().trim()
            if(!message.isEmpty()) {
                presenter.sendMessage(message)
                chat_room_et_input.setText("");
            }

        }
        presenter.connectSocket(mSocket, case_id)
    }

    override fun onStart() {
        super.onStart()
        presenter.getUser()
        presenter.loadItems(case_id, mAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disconnectSocket()
    }
}

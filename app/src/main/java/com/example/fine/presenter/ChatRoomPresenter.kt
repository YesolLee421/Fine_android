package com.example.fine.presenter

import android.content.Context
import android.content.SharedPreferences
import com.example.fine.adapter.ChatRoomAdapter
import com.example.fine.adapter.CounselListAdapter
import com.example.fine.model.*
import com.example.fine.network.RetrofitClient
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatRoomPresenter : ChatRoomContract.Presenter{
    lateinit var adapter: ChatRoomAdapter
    override lateinit var mView: ChatRoomContract.View
    override lateinit var mContext: Context
    override val TAG: String = "ChatRoomPresenter"
    lateinit var preferences: SharedPreferences
    var user: userData = userData("","","","",3, false)
    var room_id = 0
    val gson: Gson = Gson()
    lateinit var mSocket: Socket

    fun getUser() {
        preferences = mContext.getSharedPreferences("USERSIGN", 0)
        user.user_uid = preferences.getString("user_uid", "")!!
        user.email = preferences.getString("user_email", "")!!
        //user.password = preferences.getString("user_password", "")!!
        user.nickname = preferences.getString("user_nickname","")!!
        user.type = preferences.getInt("user_type", 3)
    }

    fun clearItems(adapter: ChatRoomAdapter) {
        adapter.clearItem()
    }

    fun loadItems(case_id: Int, adapter: ChatRoomAdapter) {
        room_id = case_id
        val client: OkHttpClient = RetrofitClient.getClient(mContext, "addCookie")
        val apiService = RetrofitClient.serviceAPI(client)
        val getMessage_request : Call<ServerData_messages> = apiService.getMessages(case_id)
        getMessage_request.enqueue(object : Callback<ServerData_messages>{
            override fun onFailure(call: Call<ServerData_messages>, t: Throwable) {
                executionLog(TAG, "getMessage_request 실패")
                executionLog(TAG, t.message!!)
            }

            override fun onResponse(call: Call<ServerData_messages>, response: Response<ServerData_messages>) {
                if(response.isSuccessful){
                    executionLog(TAG, "getMessage_request 성공")
                    val result: ServerData_messages = response.body()!!
                    executionLog(TAG, result.toString())
                    if(result.success){
                        for(element in result.data!!){
                            adapter.addItem(element)
                        }
                        adapter.notifyDataSetChanged()
                        executionLog(TAG, result.message!!)
                        showMessage(result.message!!)
                    }
                }
            }
        })
    }

    fun connectSocket(socket: Socket, case_id: Int) {
        mSocket = socket
        mSocket.on(Socket.EVENT_CONNECT, onConnect)
        mSocket.on("joinRoom", onJoinRoom)
        mSocket.on("chatMessage", onChatMessage)
//        mSocket.on("typing", onTyping)
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect)
        room_id = case_id
        mSocket.connect()
    }

    fun disconnectSocket() {
        mSocket.disconnect()
        mSocket.off("chatMessage", onChatMessage)
    }

    val onConnect = Emitter.Listener {
        val data : JSONObject = JSONObject()
        data.put("user_uid", user.user_uid)
        data.put("username", user.nickname)
        data.put("room", room_id)
        // user_uid 비교해서 type 설정해야할듯
        // data.put("type", 2) // notice
        mSocket.emit("connection", data)
        mView.executionLog(TAG, "user=${data}")
    }

    val onJoinRoom = Emitter.Listener {
        val data: JSONObject = JSONObject()
        data.put("username", it[0] as String)
        data.put("message", it[1] as String)
        data.put("type", 2)
        adapter.addItem(data)
        adapter.notifyDataSetChanged()
        mView.executionLog(TAG, "join user=${data}")
    }
    val onChatMessage = Emitter.Listener {
        val data: JSONObject = JSONObject()
        data.put("username", it[1] as String)
        data.put("message", it[2] as String)
        data.put("type", setMessageType(it[0] as String) )
        adapter.addItem(data)
        adapter.notifyDataSetChanged()
    }

    val onTyping = Emitter.Listener {  }
    val onDisconnect = Emitter.Listener {  }

    fun setMessageType(user_uid: String) : Int {
        if(user_uid==user.user_uid) {
            return 0
        } else {
            return 1
        }
    }

    // 채팅 보내기
    fun sendMessage(message: String) {
        val data : JSONObject = JSONObject()
        data.put("room", room_id)
        data.put("username", user.nickname)
        data.put("text", message)

        // user_uid 비교해서 type 설정해야할듯
//        data.put("type", user.type)
        mSocket.emit("chatMessage", data)
    }








    // 채팅 받기

}
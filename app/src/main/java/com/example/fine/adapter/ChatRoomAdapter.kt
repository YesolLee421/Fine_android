package com.example.fine.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import kotlinx.android.synthetic.main.item_chat_room_client.view.*
import org.json.JSONObject

class ChatRoomAdapter (
    val context: Context
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val messages: ArrayList<JSONObject> = ArrayList<JSONObject>()

    private val TYPE_MESSAGE_SENT: Int = 0
    private val TYPE_MESSAGE_RECEIVED: Int = 1
    private val TYPE_IMGAE_SENT: Int = 2
    private val TYPE_IMAGE_RECEIVED: Int = 3
    private val TYPE_NOTICE : Int = 4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            TYPE_MESSAGE_SENT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_chat_room_client, parent, false)
                return SentMessageViewHolder(view)
            }
            TYPE_MESSAGE_RECEIVED -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_chat_room_counselor, parent, false)
                return ReceivedMessageViewHolder(view)
            }
//            TYPE_IMGAE_SENT -> {
//                view.inflate(R.layout.item_chat_room_client, parent, false)
//            }
//            TYPE_IMAGE_RECEIVED -> {
//                view.inflate(R.layout.item_chat_room_client, parent, false)
//            }
            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_chat_room_notice, parent, false)
                return NoticeViewHolder(view)            }
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message: JSONObject = messages.get(position)
        when (message.getInt("type")) {
            0 -> {
                if(message.has("message")) {
                    return TYPE_MESSAGE_SENT
                } else {
                    return TYPE_IMGAE_SENT
                }
            }
            1-> {
                if(message.has("message")) {
                    return TYPE_MESSAGE_RECEIVED
                } else {
                    return TYPE_IMAGE_RECEIVED
                }
            }
            else -> return TYPE_NOTICE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SentMessageViewHolder->{

            }
            is ReceivedMessageViewHolder->{

            }
            is NoticeViewHolder->{

            }
            is SentImageViewHolder-> {

            }
            is ReceivedImageViewHolder->{

            }
        }
    }

    class SentMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val sent_content = itemView.findViewById<TextureView>(R.id.item_chat_client_content)
        val sent_time = itemView.findViewById<TextureView>(R.id.item_chat_client_time)
    }

    class ReceivedMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val received_content = itemView.findViewById<TextureView>(R.id.item_chat_counselor_content)
        val received_time = itemView.findViewById<TextureView>(R.id.item_chat_counselor_time)
    }

    class NoticeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val notice_title = itemView.findViewById<TextureView>(R.id.item_chat_notice_title)
        val notice_content = itemView.findViewById<TextureView>(R.id.item_chat_notice_content)
    }

    class SentImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // val sent_file
        val sent_time = itemView.findViewById<TextureView>(R.id.item_chat_client_time)
    }

    class ReceivedImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //val received_file
        val received_time = itemView.findViewById<TextureView>(R.id.item_chat_counselor_time)
    }

}

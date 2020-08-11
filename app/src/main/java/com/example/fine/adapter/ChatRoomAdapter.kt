package com.example.fine.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import kotlinx.android.synthetic.main.item_chat_room_client.view.*
import org.json.JSONException
import org.json.JSONObject
import java.sql.Timestamp

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
        val message: JSONObject = messages[position]
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
        val message: JSONObject = messages[position]

        when(holder) {
            is SentMessageViewHolder->{
                holder.bind(messages[position], context)
            }
            is ReceivedMessageViewHolder->{
                holder.bind(messages[position], context)
            }
            is NoticeViewHolder->{
                holder.bind(messages[position], context)
            }
            is SentImageViewHolder-> {
                //holder.bind(messages[position], context)
            }
            is ReceivedImageViewHolder->{
                //holder.bind(messages[position], context)
            }
        }
    }

//    fun changeStampToTime (jsonObject: JSONObject) : String {
//
//        return timestamp.toString()
//    }    fun changeStampToTime (jsonObject: JSONObject) : String {
////
////        return timestamp.toString()
////    }

    fun addItem (jsonObject: JSONObject) {
        messages.add(jsonObject)
        notifyDataSetChanged()
    }

    fun clearItem () {
        messages.clear()
    }



    class SentMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val sent_content = itemView.findViewById<TextView>(R.id.item_chat_client_content)
        val sent_time = itemView.findViewById<TextView>(R.id.item_chat_client_time)

        fun bind(item: JSONObject?, context: Context) {
            sent_content.text = item?.getString("message")
            //sent_time.text = changeStampToTime(item?.get("created_at"))

            //itemView.setOnLongClickListener {} // 추후 context 메뉴 등 필요하면 사용
        }

    }

    class ReceivedMessageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val received_content = itemView.findViewById<TextView>(R.id.item_chat_counselor_content)
        val received_time = itemView.findViewById<TextView>(R.id.item_chat_counselor_time)

        fun bind(item: JSONObject?, context: Context) {
            received_content.text
        }
    }

    class NoticeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val notice_title = itemView.findViewById<TextView>(R.id.item_chat_notice_title)
        val notice_content = itemView.findViewById<TextView>(R.id.item_chat_notice_content)
        fun bind(item: JSONObject?, context: Context) {
            notice_content.text = item?.getString("message")
        }
    }

    class SentImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // val sent_file
        val sent_time = itemView.findViewById<TextView>(R.id.item_chat_client_time)
//        fun bind(item: JSONObject?, context: Context) {
//            sent_time.text = item?.getString("message")
//        }
    }

    class ReceivedImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //val received_file
        val received_time = itemView.findViewById<TextView>(R.id.item_chat_counselor_time)
//        fun bind(item: JSONObject?, context: Context) {
//            received_time.text = item?.getString("message")
//        }
    }

}

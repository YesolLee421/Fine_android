package com.example.fine.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import com.example.fine.model.case_detail
import com.example.fine.presenter.CounselListPresenter
import com.example.fine.view.activity.CaseDetailActivity
import kotlinx.android.synthetic.main.activity_case_detail.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CounselListAdapter (
    val context: Context,
    var arrayList: ArrayList<case_detail?>,
    var counselListPresenter: CounselListPresenter
    ) : RecyclerView.Adapter<CounselListAdapter.CaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseViewHolder {
        Log.e("CounselListAdapter", "onCreateViewHolder 실행")
        val view = LayoutInflater.from(context).inflate(R.layout.item_counsel_list, parent, false)
        return CaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CaseViewHolder, position: Int) {
        Log.e("CounselListAdapter", "onBindViewHolder 실행")
        holder.bind(arrayList[position], context)
    }

    fun addItem(item: case_detail?){
        arrayList.add(item)
        Log.e("CounselListAdapter", "arraylist = "+ itemCount)
        Log.e("CounselListAdapter", "additem 실행 = "+ item?.counselor_name)
    }

    class CaseViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        // 레이아웃 - 모델 변수 정의
        val case_title = itemView.findViewById<TextView>(R.id.item_counsel_list_tv_name)
        val case_status = itemView.findViewById<TextView>(R.id.item_counsel_list_tv_status)
        val case_counselor_name = itemView.findViewById<TextView>(R.id.item_counsel_list_tv_counselor_name)
        val case_nextDate = itemView.findViewById<TextView>(R.id.item_counsel_list_tv_nextDate)

        fun setTitle(totalCount: Int, usedCase: Int){
            val str: StringBuilder = StringBuilder()
            when (totalCount){
                1-> str.append("1회기권");
                2-> str.append("2주 프로그램");
                4-> str.append("4주 프로그램");
                10-> str.append("10주 프로그램");
            }
            str.append(" (")
            str.append(usedCase)
            str.append("/")
            str.append(totalCount)
            str.append(")")
            case_title.text = str
        }
        fun setStatus(status: Int, context: Context){
            when(status) {
                1-> {
                    case_status.text = "진행 중"
                    case_status.setTextColor(ContextCompat.getColor(context, R.color.customDarkGreen))
                }
                2-> {
                    case_status.text = "대기 중"
                    case_status.setTextColor(ContextCompat.getColor(context, R.color.customRed))
                }
                3-> {
                    case_status.text = "완료"
                    case_status.setTextColor(ContextCompat.getColor(context, R.color.design_dark_default_color_background))
                }
            }
        }

        fun bind(item: case_detail?, context: Context){
            if(item!=null){
                // case_title 설정
                setTitle(item.totalCase, item.usedCase)

                // case_status 설정
                setStatus(item.status, context)

                // case_nextDate 설정
                val formatter = SimpleDateFormat("MM월 dd일 E a hh:mm", Locale.KOREA)
                if(item.nextCase==null){
                    case_nextDate.text = "미정"
                } else {
                    case_nextDate.text = formatter.format(item.nextCase!!)
                }

                // counselor_name
                case_counselor_name.text = "${item.counselor_name} 상담사"

                // onClick
                itemView.setOnClickListener {
                    Toast.makeText(context, "${item.case_id}번째 선택", Toast.LENGTH_SHORT ).show()
                    val intent = Intent(context, CaseDetailActivity::class.java)
                    intent.putExtra("case_id", item.case_id)
                    context.startActivity(intent)
                }
            }
        }


    }

}
package com.example.fine.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import com.example.fine.model.Case
import com.example.fine.presenter.CounselListPresenter
import com.example.fine.view.activity.CaseDetailActivity

class CounselListAdapter (
    val context: Context,
    var arrayList: ArrayList<Case?>,
    var counselListPresenter: CounselListPresenter
    ) : RecyclerView.Adapter<CounselListAdapter.CaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_counsel_list, parent, false)
        return CaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CaseViewHolder, position: Int) {
        holder.bind(arrayList[position], context)
    }

    fun addItem(item: Case?){
        arrayList.add(item)
    }

    class CaseViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        // 레이아웃 - 모델 변수 정의
        val case_title = itemView.findViewById<TextView>(R.id.item_counsel_list_tv_name)
        val case_status = itemView.findViewById<TextView>(R.id.item_counsel_list_tv_status)
        val case_symptom = itemView.findViewById<TextView>(R.id.item_counsel_list_tv_sypmtom)
        val case_nextDate = itemView.findViewById<TextView>(R.id.item_counsel_list_tv_nextDate)

        fun bind(item: Case?, context: Context){
            if(item!=null){
                // case_name 설정
                
                // case_status 설정
                
                // case_symptom 설정
                
                // case_nextDate 설정

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
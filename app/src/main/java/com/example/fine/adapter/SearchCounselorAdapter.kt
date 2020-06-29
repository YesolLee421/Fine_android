package com.example.fine.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import com.example.fine.model.CounselorData
import com.example.fine.presenter.SearchCounselorPresenter
import com.example.fine.view.activity.CounselorDetailActivity
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class SearchCounselorAdapter (
    val context: Context,
    var arrayList: ArrayList<CounselorData?>,
    var searchCounselorPresenter: SearchCounselorPresenter
) : RecyclerView.Adapter<SearchCounselorAdapter.CounselorViewHolder>() {

    fun addItem(item: CounselorData?){
        Log.e("SearchCounselorAdapter", "additem 실행 = "+ item?.name_formal)
        arrayList.add(item)
        Log.e("SearchCounselorAdapter", "arraylist = "+ itemCount)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounselorViewHolder {
        Log.e("SearchCounselorAdapter", "onCreateViewHolder 실행")
        val view = LayoutInflater.from(context).inflate(R.layout.item_search_counselor, parent, false)
        return CounselorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CounselorViewHolder, position: Int) {
        Log.e("SearchCounselorAdapter", "onBindViewHolder 실행")
        holder.bind(arrayList[position], context)
    }

    class CounselorViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        // 레이아웃 - 모델변수 정의
        val counselor_picture = itemView.findViewById<ImageView>(R.id.item_searchExp_iv_picture)
        val counselor_name = itemView.findViewById<TextView>(R.id.item_searchExp_tv_name)
        val counselor_description = itemView.findViewById<TextView>(R.id.item_searchExp_tv_description)
        val counselor_count = itemView.findViewById<TextView>(R.id.counselor_detail_tv_count)
        val counselor_count_ic = itemView.findViewById<ImageView>(R.id.counselor_detail_iv_ic_help)
        val counselor_price = itemView.findViewById<TextView>(R.id.item_searchExp_tv_price)

        fun bind(item: CounselorData?, context: Context){
            if(item!=null) {
                counselor_name.text = item.name_formal!! + " 상담사"
                counselor_description.text = item.description!!
                val nf = NumberFormat.getCurrencyInstance(Locale.KOREA)
                counselor_price.text = nf.format(item.price)
                when {
                    item.count >= 2000 ->{
                        counselor_count.text = "2000회 이상 도움"
                    }
                    item.count >= 1000 ->{
                        counselor_count.text = "1000회 이상 도움"
                    }
                    item.count >= 500 ->{
                        counselor_count.text = "500회 이상 도움"
                    }
                    item.count >= 200 -> {
                        counselor_count.text = "200회 이상 도움"
                    }
                    item.count >= 100 -> {
                        counselor_count.text = "100회 이상 도움"
                    }
                    item.count >= 50 -> {
                        counselor_count.text = "50회 이상 도움"
                    }
                    else -> {
                        counselor_count.visibility = View.INVISIBLE
                        counselor_count_ic.visibility = View.INVISIBLE
                    }
                }
                counselor_picture.setImageResource(R.drawable.logo_fine)
//                Log.e("adapter", "counselor_picture = "+ item.picture!!)

//                if(item.picture!=null){ // 추후 S3 업로드 하고 구현
//                    Picasso.get().load("http://10.0.2.2:5000/uploads/" + item.picture.toString()).into(counselor_picture);
//                }
                itemView.setOnClickListener{
                    Toast.makeText(context, counselor_name.text.toString(), Toast.LENGTH_SHORT ).show()
                    val intent = Intent(context, CounselorDetailActivity::class.java)
                    intent.putExtra("counselor_uid", item.user_uid)
                    context.startActivity(intent)
                }
            }
        }
    }
}
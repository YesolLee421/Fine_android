package com.example.fine.view.activity

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.ImageView
import android.widget.TextView
import com.example.fine.R
import com.example.fine.model.CounselorData
import com.example.fine.presenter.CounselorDetailContract
import com.example.fine.presenter.CounselorDetailPresenter
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_counselor_detail.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.NumberFormat
import java.util.*

class CounselorDetailActivity : BaseActivity(), CounselorDetailContract.View {
    override val TAG: String = "CounselorDetailActivity"

    override fun initPresenter() {
        counselorDetailPresenter = CounselorDetailPresenter()
        counselorDetailPresenter.mContext = this
        counselorDetailPresenter.mView = this
    }
    // CounselorDetailPresenter를 지연 초기화
    private lateinit var counselorDetailPresenter: CounselorDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor_detail)

        // 누르면 해당 위치로 가기
        counselor_detail_tb_about.setOnClickListener {  }
        counselor_detail_tb_career.setOnClickListener {  }
        counselor_detail_tb_time.setOnClickListener {  }

        // 상담 신청 버튼 누르면 이동
        counselor_detail_btn.setOnClickListener {
            counselorDetailPresenter.startRequestCounselActivity()
        }
    }

    override fun onStart() {
        super.onStart()
        // presenter에서 데이터 가져와서 view에 보여주기
        counselorDetailPresenter.loadData(intent.getStringExtra("counselor_uid")!!)
    }

    // 데이터 클래스 객체 받아서 view 요소에 보여주기
    override fun showInfo(counselor: CounselorData){
        counselor_detail_tv_name.text = counselor.name_formal!!
        counselor_detail_tv_description.text = counselor.description!!
        // 프로필 이미지 추후 변경
        counselor_detail_iv_picture.setImageResource(R.drawable.logo_fine)
        // 상담 횟수에 따른 변경
        setCount(counselor.count)

        // 상담사 소개 질문 3개
        counselor_detail_tv_intro_1.text = counselor.intro_1
        counselor_detail_tv_intro_2.text = counselor.intro_2
        counselor_detail_tv_intro_3.text = counselor.intro_3

        // 상담사 약력 3개
        counselor_detail_tv_certificate.text = counselor.certificate
        counselor_detail_tv_career.text = counselor.career
        counselor_detail_tv_education.text = counselor.education

        // 선호 일정
        setTimePrefered(counselor.time_prefered)

        // 가격
        val nf = NumberFormat.getCurrencyInstance(Locale.KOREA)
        counselor_detail_tv_price.text= nf.format(counselor.price)
    }

    fun setTimePrefered(time_prefered: String?){
        if(time_prefered!=null){
            val jsonData = JSONObject(time_prefered)
            val dayArray = JSONArray(jsonData.get("day").toString())
            val timeArray = JSONArray(jsonData.get("time").toString())
            val data = StringBuilder()
            if(dayArray.length()!=0){
                for(i in 0 until dayArray.length()){
                    val str_day: String = dayArray[i].toString()
                    data.append(setDay(str_day))
                    if(i!=dayArray.length()-1){
                        data.append(", ")
                    }
                }
            } else {
                data.append("요일 선택 안함")
            }
            data.append(" / ")
            if(timeArray.length()!=0){
                for(i in 0 until timeArray.length()){
                    val str_time: String = timeArray[i].toString()
                    data.append(setTime(str_time))
                    if(i!=dayArray.length()-1){
                        data.append(", ")
                    }
                }
            } else {
                data.append("시간대 선택 안함")
            }
            counselor_detail_tv_time_prefered.text = data
        }

    }

    fun setTime(time: String): String {
        when(time){
            "1"-> return "8~12"
            "2"-> return "12~16"
            "3"-> return "16~20"
            "4"-> return "20~24"
            else -> return "시간대 선택 안함"
        }
    }

    fun setDay(day: String): String{
        when(day){
            "mon"-> return "월"
            "tue"-> return "화"
            "wed"-> return "수"
            "thur"-> return "목"
            "fri"-> return "금"
            "sat"-> return "토"
            "sun"-> return "일"
            else -> return "요일 선택 안함"
        }
    }

    fun setCount(count: Int){
        when {
            count >= 2000 ->{
                counselor_detail_tv_count.text = "2000회 이상 도움"
            }
            count >= 1000 ->{
                counselor_detail_tv_count.text = "1000회 이상 도움"
            }
            count >= 500 ->{
                counselor_detail_tv_count.text = "500회 이상 도움"
            }
            count >= 200 -> {
                counselor_detail_tv_count.text = "200회 이상 도움"
            }
            count >= 100 -> {
                counselor_detail_tv_count.text = "100회 이상 도움"
            }
            count >= 50 -> {
                counselor_detail_tv_count.text = "50회 이상 도움"
            }
            else -> {
                counselor_detail_tv_count.visibility = INVISIBLE
                counselor_detail_iv_ic_help.visibility = INVISIBLE
            }
        }
    }
}

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
    override fun initPresenter() {
        counselorDetailPresenter = CounselorDetailPresenter()
        counselorDetailPresenter.mContext = this
        counselorDetailPresenter.mView = this
    }
    // CounselorDetailPresenter를 지연 초기화
    private lateinit var counselorDetailPresenter: CounselorDetailPresenter

    // 상담사 데이터 객체 저장
    var counselorData: CounselorData? = null

    lateinit var tv_name: TextView
    lateinit var tv_description: TextView
    lateinit var iv_picture: ImageView
    lateinit var iv_ic_help: ImageView
    lateinit var tv_count: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor_detail)
        // xml 요소 선언


        var tv_intro1 = findViewById<TextView>(R.id.counselor_detail_tv_intro_1)
        var tv_intro2 = findViewById<TextView>(R.id.counselor_detail_tv_intro_2)
        var tv_intro3 = findViewById<TextView>(R.id.counselor_detail_tv_intro_3)

        var tv_certificate = findViewById<TextView>(R.id.counselor_detail_tv_certificate)
        var tv_career = findViewById<TextView>(R.id.counselor_detail_tv_career)
        var tv_education = findViewById<TextView>(R.id.counselor_detail_tv_education)

        var tv_time_prefered = findViewById<TextView>(R.id.counselor_detail_tv_time_prefered)

        var tv_price = findViewById<TextView>(R.id.counselor_detail_tv_price)

        counselorData = counselorDetailPresenter.loadData(intent.getStringExtra("user_uid")!!)

        counselor_detail_tv_name.text = counselorData?.name_formal
//        counselor_detail_tv_description.text = counselorData!!.description
//        // 프로필 이미지 추후 변경
//        counselor_detail_iv_picture.setImageResource(R.drawable.logo_fine)
//        // 상담 횟수에 따른 변경
//        setCount(counselorData!!.count)
//
//        // 상담사 소개 질문 3개
//        counselor_detail_tv_intro_1.text = counselorData!!.intro_1
//        counselor_detail_tv_intro_2.text = counselorData!!.intro_2
//        counselor_detail_tv_intro_3.text = counselorData!!.intro_3
//
//        // 상담사 약력 3개
//        counselor_detail_tv_certificate.text = counselorData!!.certificate
//        counselor_detail_tv_career.text = counselorData!!.career
//        counselor_detail_tv_education.text = counselorData!!.education
//
//        // 선호 일정
//        setTimePrefered(counselorData!!.time_prefered)
//
//        // 가격
//        val nf = NumberFormat.getCurrencyInstance(Locale.KOREA)
//        counselor_detail_tv_price.text= nf.format(counselorData!!.price)

        // 누르면 해당 위치로 가기
        counselor_detail_tb_about.setOnClickListener {  }
        counselor_detail_tb_career.setOnClickListener {  }
        counselor_detail_tb_time.setOnClickListener {  }

        // 상담 신청 버튼 누르면 이동
        counselor_detail_btn.setOnClickListener {
            counselorDetailPresenter.startRequestCounselActivity()
        }
    }

    fun setTimePrefered(time: String){
        val jsonData = JSONObject(Gson().toJson(time))
        val dayArray : JSONArray = jsonData.getJSONArray("day")
        val timeArray : JSONArray = jsonData.getJSONArray("time")
        val data = StringBuilder()
        for(i in 0 until dayArray.length()-1){
            val jsonObject: JSONObject = dayArray.getJSONObject(i)
            data.append(setDay(jsonObject.getString("day")))
            if(i!=dayArray.length()-1){
                data.append(", ")
            }
        }
        data.append(" / ")
        for(i in 0 until timeArray.length()-1){
            val jsonObject: JSONObject = timeArray.getJSONObject(i)
            data.append(setTime(jsonObject.getInt("time")))
            if(i!=dayArray.length()-1){
                data.append(", ")
            }
        }
        counselor_detail_tv_time_prefered.text = data
    }

    fun setTime(time: Int): String {
        when(time){
            1-> return "8~12"
            2-> return "12~16"
            3-> return "16~20"
            4-> return "20~24"
            else -> return "선택 안함"
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
            else -> return "선택 안함"
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

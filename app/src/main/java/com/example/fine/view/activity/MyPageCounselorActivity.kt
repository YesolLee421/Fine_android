package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.fine.R
import com.example.fine.contract.MyPageCounselorContract
import com.example.fine.model.CounselorData
import com.example.fine.model.userData
import com.example.fine.presenter.MyPageCounselorPresenter
import kotlinx.android.synthetic.main.activity_my_page_counselor.*
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*

class MyPageCounselorActivity : BaseActivity(), MyPageCounselorContract.View{

    // CounselorDetailPresenter를 지연 초기화
    private lateinit var myPageCounselorPresenter : MyPageCounselorPresenter
    val TAG = "MyPageCounselorActivity"

    fun setPrice(counselor: CounselorData) {
        val str = StringBuilder()
        val nf = NumberFormat.getCurrencyInstance(Locale.KOREA)
        str.append(nf.format(counselor.price))
            .append(" / ")
        if(counselor.discount_4w==0 && counselor.discount_10w==0){
            str.append("할인 없음")
        } else {
            str.append("할인 있음")
        }
        my_page_counselor_tv_price.text = str
    }

    override fun showInfo(user: userData, counselor: CounselorData) {
        if(counselor.intro_1==null || counselor.intro_2==null || counselor.intro_3==null){
            // 하나라도 비어있으면 작성 중
            my_page_counselor_tv_intro.text = "작성 중"
            my_page_counselor_tv_intro.setTextColor(ContextCompat.getColor(this, R.color.customRed))
        } else {
            my_page_counselor_tv_intro.text = "작성 완료"
            my_page_counselor_tv_intro.setTextColor(ContextCompat.getColor(this, R.color.customDarkGreen))
        }

        if(counselor.certificate==null || counselor.career==null || counselor.education==null){
            my_page_counselor_tv_career.text = "작성 중"
            my_page_counselor_tv_career.setTextColor(ContextCompat.getColor(this, R.color.customRed))
        } else {
            my_page_counselor_tv_career.text = "작성 완료"
            my_page_counselor_tv_career.setTextColor(ContextCompat.getColor(this, R.color.customDarkGreen))
        }

        if(counselor.bank_account==null){
            my_page_counselor_tv_bank_account.text = "미등록"
            my_page_counselor_tv_bank_account.setTextColor(ContextCompat.getColor(this, R.color.customRed))
        } else {
            my_page_counselor_tv_bank_account.text = "등록 완료"
            my_page_counselor_tv_bank_account.setTextColor(ContextCompat.getColor(this, R.color.customDarkGreen))
        }

        setPrice(counselor)

        if(counselor.time_prefered==null){
            my_page_counselor_tv_time.text = "미등록"
            my_page_counselor_tv_time.setTextColor(ContextCompat.getColor(this, R.color.customRed))
        } else {
            my_page_counselor_tv_bank_account.text = counselor.time_prefered
            my_page_counselor_tv_bank_account.setTextColor(ContextCompat.getColor(this, R.color.customDarkGreen))
        }
    }

    override fun initPresenter() {
        myPageCounselorPresenter = MyPageCounselorPresenter()
        myPageCounselorPresenter.mContext = this
        myPageCounselorPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_counselor)

        myPageCounselorPresenter.loadData()

        my_page_counselor_profile.setOnClickListener {
            myPageCounselorPresenter.startChangeProfileActivity()
        }
        my_page_counselor_password.setOnClickListener {
            myPageCounselorPresenter.startChangePasswordActivity()
        }
        my_page_counselor_intro.setOnClickListener {
            myPageCounselorPresenter.startChangeIntroActivity()
        }
        my_page_counselor_career.setOnClickListener {
            myPageCounselorPresenter.startChangeCareerActivity()
        }
        my_page_counselor_time.setOnClickListener {
            myPageCounselorPresenter.startChangeTimePreferedActivity()
        }
        my_page_counselor_price.setOnClickListener {
            myPageCounselorPresenter.startChangePriceActivity()
        }
        my_page_counselor_bank_account.setOnClickListener {
            myPageCounselorPresenter.startChangeBankAccountActivity()
        }
        my_page_counselor_counsel_list.setOnClickListener {
            myPageCounselorPresenter.startCounselListActivity()
        }
        my_page_counselor_setting.setOnClickListener {
            myPageCounselorPresenter.startSettingsActivity()
        }

    }
}

package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.fine.R
import com.example.fine.R.color.customDarkGreen
import com.example.fine.R.color.customRed
import com.example.fine.model.userData
import com.example.fine.presenter.MyPageContract
import com.example.fine.presenter.MyPagePresenter
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPageActivity : BaseActivity() , MyPageContract.View{

    // CounselorDetailPresenter를 지연 초기화
    private lateinit var myPagePresenter : MyPagePresenter


    override fun showInfo(user: userData) {
        if(user.nickname!=""){
            my_page_tv_nickname.text = user.nickname
        }
        if(user.hasPaper){
            my_page_tv_hasPaper.text = "작성 완료"
            my_page_tv_hasPaper.setTextColor(ContextCompat.getColor(this, R.color.customDarkGreen))
        } else {
            my_page_tv_hasPaper.text = "작성 중"
            my_page_tv_hasPaper.setTextColor(ContextCompat.getColor(this, R.color.customRed))
        }
    }

    override fun initPresenter() {
        myPagePresenter = MyPagePresenter()
        myPagePresenter.mContext = this
        myPagePresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        myPagePresenter.loadData()

        my_page_nick_name.setOnClickListener {
            myPagePresenter.startChangeNickNameActivity()
        }
        my_page_password.setOnClickListener {
            myPagePresenter.startChangePasswordActivity()
        }
        my_page_counsel_paper.setOnClickListener {
            myPagePresenter.startCheckPaperActivity()
        }
        my_page_agreement.setOnClickListener {
            myPagePresenter.startNoticeActivity()
        }
        my_page_counsel_list.setOnClickListener {
            myPagePresenter.startCounselListActivity()
        }
        my_page_settings.setOnClickListener {
            myPagePresenter.startSettingsActivity()
        }

    }
}

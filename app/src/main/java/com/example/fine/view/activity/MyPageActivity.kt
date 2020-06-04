package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPageActivity : BaseActivity() {
    override fun initPresenter() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        my_page_nick_name.setOnClickListener {
            startActivity(Intent(this, ChangeNickNameActivity::class.java))
        }
        my_page_password.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }
        my_page_counsel_paper.setOnClickListener {
            startActivity(Intent(this, CheckCounselPaperActivity::class.java))
        }
        my_page_agreement.setOnClickListener {
            startActivity(Intent(this, NoticeActivity::class.java))
        }
        my_page_settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

    }
}

package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import kotlinx.android.synthetic.main.activity_my_page_counselor.*

class MyPageCounselorActivity : BaseActivity() {
    override fun initPresenter() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page_counselor)

        my_page_counselor_profile.setOnClickListener {
            startActivity(Intent(this, ChangeNickNameActivity::class.java))
        }
        my_page_counselor_password.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }
        my_page_counselor_intro.setOnClickListener {
            startActivity(Intent(this, ChangeCounselorIntroActivity::class.java))
        }
        my_page_counselor_career.setOnClickListener {
            startActivity(Intent(this, ChangeCounselorCareerActivity::class.java))
        }
        my_page_counselor_time.setOnClickListener {
            startActivity(Intent(this, ChangeCounselorTimePreferedActivity::class.java))
        }
        my_page_counselor_price.setOnClickListener {
            startActivity(Intent(this, ChangeCounselorPriceActivity::class.java))
        }
        my_page_counselor_bank_account.setOnClickListener {
            startActivity(Intent(this, ChangeCounselorBankAccountActivity::class.java))
        }
        my_page_counselor_counsel_list.setOnClickListener {
            startActivity(Intent(this, CounselListActivity::class.java))
        }
        my_page_counselor_setting.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

    }
}

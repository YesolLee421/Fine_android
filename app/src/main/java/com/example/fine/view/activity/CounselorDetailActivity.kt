package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import kotlinx.android.synthetic.main.activity_counselor_detail.*

class CounselorDetailActivity : BaseActivity() {
    override fun initPresenter() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor_detail)

        counselor_detail_btn.setOnClickListener {
            startActivity(Intent(this, CheckPaymentActivity::class.java))
        }
    }
}

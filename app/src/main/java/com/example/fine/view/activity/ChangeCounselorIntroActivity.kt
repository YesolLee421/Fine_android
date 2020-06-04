package com.example.fine.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R

class ChangeCounselorIntroActivity : BaseActivity() {
    override fun initPresenter() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_counselor_intro)
    }
}

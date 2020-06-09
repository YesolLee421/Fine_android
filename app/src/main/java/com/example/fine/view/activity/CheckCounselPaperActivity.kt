package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fine.R
import kotlinx.android.synthetic.main.activity_check_counsel_paper.*

class CheckCounselPaperActivity : BaseActivity() {
    override fun initPresenter() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_counsel_paper)

        check_paper_info1_btn.setOnClickListener {
            goToWritePaper(1)
        }
        check_paper_info2_btn.setOnClickListener {
            goToWritePaper(2)
        }
        check_paper_info3_btn.setOnClickListener {
            goToWritePaper(3)
        }
    }

    // 나중에 presenter에서 오버라이드
    fun goToWritePaper(page: Int){
        val intent: Intent = Intent(this@CheckCounselPaperActivity, WriteCounselPaperActivity::class.java)
        intent.putExtra("page", page)
        startActivity(intent)
    }
}

package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fine.R
import kotlinx.android.synthetic.main.activity_counsel_list.*

class CounselListActivity : BaseActivity() {
    override fun initPresenter() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counsel_list)

        counsel_list_card.setOnClickListener {
            startActivity(Intent(this@CounselListActivity, CaseDetailActivity::class.java))
        }

        counsel_list_bot_nav.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.search_counselor ->{
                    Toast.makeText(this, "상담사찾기", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@CounselListActivity, SearchCounselorActivity::class.java))
                }
                R.id.get_counsel ->{
                    Toast.makeText(this, "상담하기", Toast.LENGTH_SHORT).show()
                }
                R.id.my_page ->{
                    Toast.makeText(this, "마이페이지", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MyPageActivity::class.java)
                    intent.putExtra("type",0)
                    startActivity(intent)
                }
                R.id.my_page_counselor ->{
                    Toast.makeText(this, "상담사 마이페이지", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MyPageCounselorActivity::class.java)
                    intent.putExtra("type",1)
                    startActivity(intent)
                }
            }
        }
    }
}

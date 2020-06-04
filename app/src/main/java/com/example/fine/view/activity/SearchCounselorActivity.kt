package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fine.R
import kotlinx.android.synthetic.main.activity_search_counselor.*

class SearchCounselorActivity : BaseActivity() {
    override fun initPresenter() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_counselor)

        search_counselor_card.setOnClickListener {
            startActivity(Intent(this@SearchCounselorActivity, CounselorDetailActivity::class.java))
        }

        activity_search_counselor_ic_filter.setOnClickListener {
            startActivity(Intent(this@SearchCounselorActivity, CounselorFilterActivity::class.java))
        }

        search_counselor_bot_nav.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.search_counselor ->{
                    Toast.makeText(this, "상담사찾기",Toast.LENGTH_SHORT).show()
                }
                R.id.get_counsel ->{
                    Toast.makeText(this, "상담하기",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SearchCounselorActivity, CounselListActivity::class.java))
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

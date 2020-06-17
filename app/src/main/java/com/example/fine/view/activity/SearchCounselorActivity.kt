package com.example.fine.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import com.example.fine.adapter.SearchCounselorAdapter
import com.example.fine.model.CounselorData
import com.example.fine.presenter.SearchCounselorContract
import com.example.fine.presenter.SearchCounselorPresenter
import kotlinx.android.synthetic.main.activity_search_counselor.*

class SearchCounselorActivity : BaseActivity(), SearchCounselorContract.View {
    override val TAG = "SearchCounselorActivity"

    // 리사이클러뷰 관련 요소
    // 리사이클러뷰
    lateinit var mList: RecyclerView
    // 레이아웃 매니저
    lateinit var lm: LinearLayoutManager
    // 어댑터
    lateinit var mAdapter: SearchCounselorAdapter
    // 리스트
    var arrayList = ArrayList<CounselorData?>()


    // LoginActivity와 함께 생성될 LoginPresenter를 지연 초기화
    private lateinit var searchCounselorPresenter: SearchCounselorPresenter

    override fun initPresenter() {
        searchCounselorPresenter = SearchCounselorPresenter()
        searchCounselorPresenter.mContext = this
        searchCounselorPresenter.mView = this
    }

    fun setRecyclerView(){
        mList = findViewById(R.id.search_counselor_rv)

        lm = LinearLayoutManager(this)
        mList.layoutManager = lm

        mAdapter = SearchCounselorAdapter(this, arrayList, searchCounselorPresenter)
        mList.adapter = mAdapter
//        mList.setHasFixedSize(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_counselor)

        // 리사이클러뷰에 요소 연결하고 정보 불러오기
        setRecyclerView()

        // 유저 정보 받기
        searchCounselorPresenter.getUser()

        search_counselor_iv_ic_filter.setOnClickListener {
            startActivity(Intent(this@SearchCounselorActivity, CounselorFilterActivity::class.java))
        }

        search_counselor_bot_nav.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.search_counselor ->{
                    Toast.makeText(this, "상담사찾기",Toast.LENGTH_SHORT).show()
                }
                R.id.get_counsel ->{
                    Toast.makeText(this, "상담하기",Toast.LENGTH_SHORT).show()
                    searchCounselorPresenter.startCounselListActivity()
                }
                R.id.my_page ->{
                    Toast.makeText(this, "마이페이지", Toast.LENGTH_SHORT).show()
                    searchCounselorPresenter.startMypageActivity()

                }
//                R.id.my_page_counselor ->{
//                    Toast.makeText(this, "상담사 마이페이지", Toast.LENGTH_SHORT).show()
//
//                    val intent = Intent(this, MyPageCounselorActivity::class.java)
//                    intent.putExtra("type",1)
//                    startActivity(intent)
//                }
            }
        }


    }

    override fun onStart() {
        super.onStart()
        searchCounselorPresenter.clearItems(arrayList, mAdapter)
        searchCounselorPresenter.loadItems(arrayList, mAdapter)
        mAdapter.notifyDataSetChanged()
    }
}

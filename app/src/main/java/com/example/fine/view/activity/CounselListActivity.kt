package com.example.fine.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fine.R
import com.example.fine.adapter.CounselListAdapter
import com.example.fine.model.Case
import com.example.fine.model.case_detail
import com.example.fine.presenter.CounselListContract
import com.example.fine.presenter.CounselListPresenter
import kotlinx.android.synthetic.main.activity_counsel_list.*

class CounselListActivity : BaseActivity(), CounselListContract.View {
    override val TAG: String = "CounselListActivity"

    // 리사이클러뷰 관련 요소
    // 리사이클러뷰
    lateinit var mList: RecyclerView
    // 레이아웃 매니저
    lateinit var lm: LinearLayoutManager
    // 어댑터
    lateinit var mAdapter: CounselListAdapter
    // 리스트
    var arrayList = ArrayList<case_detail?>()

    // Activity와 함께 생성될 Presenter를 지연 초기화
    private lateinit var counselListPresenter: CounselListPresenter

    override fun initPresenter() {
        counselListPresenter = CounselListPresenter()
        counselListPresenter.mContext = this
        counselListPresenter.mView = this
    }

    fun setRecyclerView() {
        mList = findViewById(R.id.counsel_list_rv)

        lm = LinearLayoutManager(this)
        mList.layoutManager = lm

        mAdapter = CounselListAdapter(this, arrayList, counselListPresenter)
        mList.adapter = mAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counsel_list)

        // 리사이클러뷰에 요소 연결하고 정보 불러오기
        setRecyclerView()

        // 유저 정보 받기
        counselListPresenter.getUser()

        counsel_list_bot_nav.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.search_counselor ->{
                    Toast.makeText(this, "상담사찾기", Toast.LENGTH_SHORT).show()
                    counselListPresenter.startCounselorListActivity()
                }
                R.id.get_counsel ->{
                    Toast.makeText(this, "상담하기", Toast.LENGTH_SHORT).show()
                }
                R.id.my_page ->{
                    Toast.makeText(this, "마이페이지", Toast.LENGTH_SHORT).show()
                    counselListPresenter.startMypageActivity()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        counselListPresenter.clearItems(arrayList, mAdapter)
        counselListPresenter.loadItems(arrayList, mAdapter)
        mAdapter.notifyDataSetChanged()
    }
}

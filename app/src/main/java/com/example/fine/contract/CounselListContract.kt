package com.example.fine.presenter

import com.example.fine.adapter.CounselListAdapter
import com.example.fine.model.Case
import com.example.fine.view.BaseView

interface CounselListContract {
    interface View : BaseView {}
    interface Presenter : BasePresenter<View>{
        fun getUser()

        fun startMypageActivity()

        fun startCounselorListActivity()

        // 리스트 지우기
        fun clearItems(
            list: ArrayList<Case?>,
            adapter: CounselListAdapter
        )

        // 전체 상담사 리스트
        fun loadItems(
            list: ArrayList<Case?>,
            adapter: CounselListAdapter
        )
    }
}
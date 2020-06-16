package com.example.fine.presenter

import com.example.fine.adapter.SearchCounselorAdapter
import com.example.fine.model.CounselorData
import com.example.fine.view.BaseView

interface SearchCounselorContract {
    interface View: BaseView {}
    interface Presenter: BasePresenter<View>{

        fun getUser()

        fun startMypageActivity()

        fun startCounselListActivity()

        // 리스트 지우기
        fun clearItems(
            list: ArrayList<CounselorData?>,
            adapter: SearchCounselorAdapter
        )

        // 전체 상담사 리스트
        fun loadItems(
            list: ArrayList<CounselorData?>,
            adapter: SearchCounselorAdapter
        )

        // 정렬

        // 필터


    }
}
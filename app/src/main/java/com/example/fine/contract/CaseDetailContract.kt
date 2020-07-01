package com.example.fine.presenter

import com.example.fine.model.case_detail
import com.example.fine.view.BaseView


interface CaseDetailContract {
    interface View : BaseView{
        fun showInfo(case: case_detail)
    }
    interface Presenter : BasePresenter<View>{
        fun startChatRoomActivity()
        fun loadData (case_id: Int)
    }
}
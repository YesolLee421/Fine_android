package com.example.fine.presenter

import com.example.fine.model.CounselorData
import com.example.fine.view.BaseView

interface CounselorDetailContract {
    interface View : BaseView {
        fun showInfo(counselor: CounselorData)
    }
    interface Presenter : BasePresenter<View>{

        fun startRequestCounselActivity()
        fun loadData (user_uid: String)
    }
}
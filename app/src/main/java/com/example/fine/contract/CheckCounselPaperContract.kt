package com.example.fine.presenter

import com.example.fine.model.Paper
import com.example.fine.view.BaseView

interface CheckCounselPaperContract {
    interface View : BaseView {
        fun showInfo(paper: Paper)
    }
    interface Presenter : BasePresenter<View>{
        fun getUser()
        fun loadData (paper_id: Int)
        fun startWritePaperActivity(page: Int)
    }
}
package com.example.fine.presenter

import androidx.appcompat.app.AlertDialog
import com.example.fine.model.Paper
import com.example.fine.model.case_detail
import com.example.fine.view.BaseView

interface CheckCounselPaperContract {
    interface View : BaseView {
        fun showInfo(paper: Paper)
        fun showDialog (caseDetail: case_detail?)
    }
    interface Presenter : BasePresenter<View>{
        fun getUser()
        fun loadData (paper_id: Int)
        fun startWritePaperActivity(page: Int)
    }
}